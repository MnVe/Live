# 大学生校园指南 - 全方位校园生活服务平台

## 项目简介

大学生校园指南是一个基于Spring Boot 3 + Vue 3 + Tailwind CSS开发的现代化校园生活服务平台。为大学生提供入学指导、校园服务、学习资源、社交互动等全方位服务，致力于让每一位学生都能快速适应并享受美好的大学生活。

## 主要功能

### 🎯 核心功能
- **入学指南**: 详细的新生入学流程指导，包括报到、宿舍、选课等
- **校园服务**: 整合校内各类服务资源，提供便民信息查询
- **社区交流**: 学生互动平台，经验分享、问题求助、活动发布
- **智能问答**: 基于关键词匹配的智能问答系统，7×24小时在线咨询

### 🚀 创新特性
- **个性化推荐**: 基于用户行为的智能推荐算法
- **响应式设计**: 完美适配PC、平板、手机等各种设备
- **实时交互**: 现代化的用户界面和流畅的交互体验
- **权限管理**: 完善的用户权限和角色管理系统

## 技术栈

### 后端技术
- **框架**: Spring Boot 3.2.0
- **数据库**: MySQL 8.0 / H2 (开发环境)
- **安全**: Spring Security + JWT
- **ORM**: Spring Data JPA + Hibernate
- **构建工具**: Maven 3.8+
- **Java版本**: JDK 17+

### 前端技术
- **框架**: Vue 3.3+ (Composition API)
- **构建工具**: Vite 4.4+
- **UI框架**: Tailwind CSS 3.3+
- **状态管理**: Pinia 2.1+
- **路由**: Vue Router 4.2+
- **HTTP客户端**: Axios 1.6+
- **图标**: Heroicons

## 快速开始

### 环境要求
- Node.js 16+ 
- Java 17+
- Maven 3.8+
- MySQL 8.0+ (可选，默认使用H2)

### 后端启动

1. 克隆项目
```bash
git clone [repository-url]
cd campus-guide/backend
```

2. 安装依赖
```bash
mvn clean install
```

3. 配置数据库（可选）
```yaml
# 修改 src/main/resources/application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/campus_guide
    username: your_username
    password: your_password
```

4. 启动应用
```bash
mvn spring-boot:run
```

后端服务将在 http://localhost:8080 启动

### 前端启动

1. 进入前端目录
```bash
cd campus-guide/frontend
```

2. 安装依赖
```bash
npm install
```

3. 启动开发服务器
```bash
npm run dev
```

前端应用将在 http://localhost:3000 启动

## 项目结构

```
campus-guide/
├── backend/                    # 后端Spring Boot项目
│   ├── src/main/java/
│   │   └── com/university/campusguide/
│   │       ├── config/         # 配置类
│   │       ├── controller/     # 控制器
│   │       ├── dto/           # 数据传输对象
│   │       ├── entity/        # 实体类
│   │       ├── repository/    # 数据访问层
│   │       ├── security/      # 安全配置
│   │       └── service/       # 业务逻辑层
│   ├── src/main/resources/
│   │   ├── application.yml    # 应用配置
│   │   └── data.sql          # 初始化数据
│   └── pom.xml               # Maven配置
├── frontend/                   # 前端Vue项目
│   ├── src/
│   │   ├── components/        # 可复用组件
│   │   ├── views/            # 页面组件
│   │   ├── router/           # 路由配置
│   │   ├── stores/           # Pinia状态管理
│   │   ├── utils/            # 工具函数
│   │   └── style.css         # 全局样式
│   ├── public/               # 静态资源
│   ├── package.json          # 项目依赖
│   ├── vite.config.js        # Vite配置
│   └── tailwind.config.js    # Tailwind配置
└── README.md                  # 项目说明
```

## API文档

### 认证相关
- `POST /api/auth/login` - 用户登录
- `POST /api/auth/register` - 用户注册
- `POST /api/auth/logout` - 用户登出

### 用户管理
- `GET /api/users/me` - 获取当前用户信息
- `PUT /api/users/{id}` - 更新用户信息
- `GET /api/users/{id}` - 获取用户详情

### 指南管理
- `GET /api/guides` - 获取指南列表
- `GET /api/guides/{id}` - 获取指南详情
- `POST /api/guides` - 创建指南
- `PUT /api/guides/{id}` - 更新指南
- `DELETE /api/guides/{id}` - 删除指南

### 服务管理
- `GET /api/services` - 获取服务列表
- `GET /api/services/{id}` - 获取服务详情
- `GET /api/services/category/{category}` - 按分类获取服务

### 智能问答
- `GET /api/qa/search` - 搜索问答
- `GET /api/qa/top` - 获取热门问题
- `GET /api/qa/category/{category}` - 按分类获取问答

### 推荐系统
- `GET /api/recommendations/guides` - 获取推荐指南
- `POST /api/recommendations/behavior` - 记录用户行为

## 默认账户

系统预置了以下测试账户：

| 用户类型 | 用户名 | 密码 | 说明 |
|---------|--------|------|------|
| 管理员 | admin | admin123 | 系统管理员，拥有所有权限 |
| 教师 | teacher | teacher123 | 教师用户，可发布指南和管理内容 |
| 学生 | student | student123 | 普通学生用户 |

## 部署说明

### 生产环境部署

1. **后端部署**
```bash
# 构建JAR包
mvn clean package -Dmaven.test.skip=true

# 运行应用
java -jar target/campus-guide-1.0.0.jar
```

2. **前端部署**
```bash
# 构建生产版本
npm run build

# 部署到Web服务器
# 将dist目录下的文件部署到nginx或Apache等Web服务器
```

3. **数据库配置**
- 生产环境建议使用MySQL
- 修改application.yml中的数据库配置
- 确保数据库已创建并具有相应权限

### Docker部署（可选）

项目支持Docker容器化部署：

```bash
# 构建镜像
docker build -t campus-guide .

# 运行容器
docker run -p 8080:8080 -p 3000:3000 campus-guide
```

## 贡献指南

我们欢迎任何形式的贡献！请遵循以下步骤：

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 开发规范

### 代码风格
- Java: 遵循Google Java Style Guide
- JavaScript: 使用ESLint + Prettier
- 提交信息: 遵循Conventional Commits规范

### 分支管理
- `main`: 主分支，用于生产环境
- `develop`: 开发分支，用于集成新功能
- `feature/*`: 功能分支
- `hotfix/*`: 热修复分支

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系我们

- 邮箱: contact@campusguide.edu
- 官网: https://campusguide.edu
- 问题反馈: [GitHub Issues](https://github.com/your-repo/campus-guide/issues)

## 更新日志

### v1.0.0 (2024-02-20)
- 🎉 项目初始版本发布
- ✨ 完成核心功能开发
- 🚀 智能问答系统上线
- 💡 个性化推荐功能
- 🎨 现代化UI设计
- 📱 响应式布局优化

---

感谢您使用大学生校园指南平台！如果这个项目对您有帮助，请给我们一个 ⭐️