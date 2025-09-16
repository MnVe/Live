#!/bin/bash

# 大学生校园指南 - 开发环境启动脚本

echo "🎓 大学生校园指南 - 开发环境启动"
echo "=================================="

# 检查是否安装了必要的工具
check_command() {
    if ! command -v $1 &> /dev/null; then
        echo "❌ $1 未安装，请先安装 $1"
        exit 1
    fi
}

echo "🔍 检查环境依赖..."
check_command "java"
check_command "mvn"
check_command "node"
check_command "npm"

# 检查Java版本
java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
if [ "$java_version" -lt 17 ]; then
    echo "❌ Java版本需要17或更高，当前版本: $java_version"
    exit 1
fi

echo "✅ 环境检查通过"

# 启动后端
echo ""
echo "🚀 启动后端服务..."
cd backend

# 检查是否需要安装依赖
if [ ! -d "target" ]; then
    echo "📦 安装后端依赖..."
    mvn clean install -DskipTests
fi

# 启动Spring Boot应用
echo "🌟 启动Spring Boot应用..."
mvn spring-boot:run &
BACKEND_PID=$!

# 等待后端启动
echo "⏳ 等待后端服务启动..."
for i in {1..30}; do
    if curl -s http://localhost:8080/api/actuator/health > /dev/null 2>&1; then
        echo "✅ 后端服务启动成功"
        break
    fi
    if [ $i -eq 30 ]; then
        echo "❌ 后端服务启动超时"
        kill $BACKEND_PID 2>/dev/null
        exit 1
    fi
    sleep 2
done

# 启动前端
echo ""
echo "🎨 启动前端服务..."
cd ../frontend

# 检查是否需要安装依赖
if [ ! -d "node_modules" ]; then
    echo "📦 安装前端依赖..."
    npm install
fi

# 启动Vite开发服务器
echo "🌟 启动Vite开发服务器..."
npm run dev &
FRONTEND_PID=$!

# 等待前端启动
echo "⏳ 等待前端服务启动..."
for i in {1..20}; do
    if curl -s http://localhost:3000 > /dev/null 2>&1; then
        echo "✅ 前端服务启动成功"
        break
    fi
    if [ $i -eq 20 ]; then
        echo "❌ 前端服务启动超时"
        kill $BACKEND_PID $FRONTEND_PID 2>/dev/null
        exit 1
    fi
    sleep 2
done

echo ""
echo "🎉 所有服务启动成功！"
echo "=================================="
echo "📱 前端地址: http://localhost:3000"
echo "🔧 后端API: http://localhost:8080/api"
echo "💾 H2数据库: http://localhost:8080/api/h2-console"
echo ""
echo "📋 测试账户:"
echo "   管理员: admin / admin123"
echo "   学生: student / student123"
echo "   教师: teacher / teacher123"
echo ""
echo "按 Ctrl+C 停止所有服务"
echo "=================================="

# 等待用户中断
trap "echo ''; echo '🛑 停止所有服务...'; kill $BACKEND_PID $FRONTEND_PID 2>/dev/null; exit 0" INT

# 保持脚本运行
wait