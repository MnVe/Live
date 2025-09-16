# 多阶段构建 Dockerfile

# 阶段1: 构建前端
FROM node:18-alpine AS frontend-builder
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm ci --only=production
COPY frontend/ ./
RUN npm run build

# 阶段2: 构建后端
FROM maven:3.8.6-openjdk-17-slim AS backend-builder
WORKDIR /app/backend
COPY backend/pom.xml ./
RUN mvn dependency:go-offline -B
COPY backend/src ./src
RUN mvn clean package -DskipTests

# 阶段3: 运行时环境
FROM openjdk:17-jre-slim

# 安装nginx用于提供前端静态文件
RUN apt-get update && apt-get install -y nginx && rm -rf /var/lib/apt/lists/*

# 创建应用目录
WORKDIR /app

# 复制后端JAR文件
COPY --from=backend-builder /app/backend/target/*.jar app.jar

# 复制前端构建文件到nginx目录
COPY --from=frontend-builder /app/frontend/dist /usr/share/nginx/html

# 复制nginx配置
COPY docker/nginx.conf /etc/nginx/nginx.conf

# 复制启动脚本
COPY docker/start.sh /app/start.sh
RUN chmod +x /app/start.sh

# 暴露端口
EXPOSE 80 8080

# 设置环境变量
ENV SPRING_PROFILES_ACTIVE=docker

# 启动应用
CMD ["/app/start.sh"]