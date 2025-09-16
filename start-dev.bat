@echo off
chcp 65001 >nul
title 大学生校园指南 - 开发环境启动

echo 🎓 大学生校园指南 - 开发环境启动
echo ==================================

echo 🔍 检查环境依赖...

:: 检查Java
java -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Java未安装，请先安装Java 17或更高版本
    pause
    exit /b 1
)

:: 检查Maven
mvn -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Maven未安装，请先安装Maven
    pause
    exit /b 1
)

:: 检查Node.js
node -version >nul 2>&1
if errorlevel 1 (
    echo ❌ Node.js未安装，请先安装Node.js 16或更高版本
    pause
    exit /b 1
)

:: 检查npm
npm -version >nul 2>&1
if errorlevel 1 (
    echo ❌ npm未安装，请先安装npm
    pause
    exit /b 1
)

echo ✅ 环境检查通过

:: 启动后端
echo.
echo 🚀 启动后端服务...
cd backend

:: 检查target目录
if not exist "target" (
    echo 📦 安装后端依赖...
    call mvn clean install -DskipTests
)

echo 🌟 启动Spring Boot应用...
start "Backend" cmd /c "mvn spring-boot:run"

:: 等待后端启动
echo ⏳ 等待后端服务启动...
timeout /t 15 /nobreak >nul

:: 启动前端
echo.
echo 🎨 启动前端服务...
cd ..\frontend

:: 检查node_modules目录
if not exist "node_modules" (
    echo 📦 安装前端依赖...
    call npm install
)

echo 🌟 启动Vite开发服务器...
start "Frontend" cmd /c "npm run dev"

:: 等待前端启动
echo ⏳ 等待前端服务启动...
timeout /t 10 /nobreak >nul

echo.
echo 🎉 所有服务启动成功！
echo ==================================
echo 📱 前端地址: http://localhost:3000
echo 🔧 后端API: http://localhost:8080/api
echo 💾 H2数据库: http://localhost:8080/api/h2-console
echo.
echo 📋 测试账户:
echo    管理员: admin / admin123
echo    学生: student / student123
echo    教师: teacher / teacher123
echo.
echo 关闭此窗口将不会停止服务，请手动关闭服务窗口
echo ==================================

:: 打开浏览器
start http://localhost:3000

pause