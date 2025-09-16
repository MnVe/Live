package com.university.campusguide.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.campusguide.entity.*;
import com.university.campusguide.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final GuideRepository guideRepository;
    private final CampusServiceRepository campusServiceRepository;
    private final PostRepository postRepository;
    private final QABotRepository qaBotRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            initializeData();
        }
    }
    
    private void initializeData() throws Exception {
        // 创建管理员用户
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@university.edu");
        admin.setNickname("系统管理员");
        admin.setRole(User.Role.ADMIN);
        admin.setStatus(User.UserStatus.ACTIVE);
        admin = userRepository.save(admin);
        
        // 创建示例学生用户
        User student = new User();
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("student123"));
        student.setEmail("student@university.edu");
        student.setNickname("小明");
        student.setStudentId("2024001");
        student.setMajor("计算机科学与技术");
        student.setGrade("2024级");
        student.setCollege("计算机学院");
        student.setGender(User.Gender.MALE);
        student.setBirthDate(LocalDate.of(2005, 8, 15));
        student.setRole(User.Role.STUDENT);
        student.setStatus(User.UserStatus.ACTIVE);
        student.setPoints(100);
        student = userRepository.save(student);
        
        // 创建示例老师用户
        User teacher = new User();
        teacher.setUsername("teacher");
        teacher.setPassword(passwordEncoder.encode("teacher123"));
        teacher.setEmail("teacher@university.edu");
        teacher.setNickname("王老师");
        teacher.setCollege("计算机学院");
        teacher.setRole(User.Role.TEACHER);
        teacher.setStatus(User.UserStatus.ACTIVE);
        teacher = userRepository.save(teacher);
        
        // 初始化入学指南
        initializeGuides(admin, teacher);
        
        // 初始化校园服务
        initializeCampusServices();
        
        // 初始化社区帖子
        initializePosts(student, teacher);
        
        // 初始化智能问答
        initializeQABot();
    }
    
    private void initializeGuides(User admin, User teacher) throws Exception {
        List<String> tags1 = Arrays.asList("新生", "入学", "报到", "必备");
        List<String> images1 = Arrays.asList("/images/guide1.jpg", "/images/guide2.jpg");
        
        Guide guide1 = new Guide();
        guide1.setTitle("新生入学报到完整指南");
        guide1.setSummary("详细介绍新生入学报到的全流程，包括报到时间、地点、所需材料等重要信息。");
        guide1.setContent("## 新生入学报到指南\n\n### 报到时间\n- 本科生：9月1日-9月2日\n- 研究生：8月30日-8月31日\n\n### 报到地点\n- 计算机学院：信息楼一楼大厅\n- 经济管理学院：管理楼二楼\n\n### 必备材料\n1. 录取通知书\n2. 身份证原件及复印件\n3. 户口迁移证（如需迁户口）\n4. 党团关系转移材料\n5. 体检报告\n\n### 注意事项\n- 请提前预约报到时间\n- 建议家长陪同\n- 准备好缴费银行卡");
        guide1.setCategory(Guide.GuideCategory.ADMISSION);
        guide1.setTags(objectMapper.writeValueAsString(tags1));
        guide1.setImages(objectMapper.writeValueAsString(images1));
        guide1.setCoverImage("/images/guide_cover1.jpg");
        guide1.setStatus(Guide.GuideStatus.PUBLISHED);
        guide1.setPriority(10);
        guide1.setAuthor(admin);
        guide1.setViewCount(156);
        guide1.setLikeCount(23);
        guideRepository.save(guide1);
        
        List<String> tags2 = Arrays.asList("宿舍", "生活", "住宿", "规则");
        Guide guide2 = new Guide();
        guide2.setTitle("宿舍生活指南");
        guide2.setSummary("介绍宿舍分配、生活规则、设施使用等相关信息，帮助新生快速适应宿舍生活。");
        guide2.setContent("## 宿舍生活指南\n\n### 宿舍分配\n- 本科生：4人间，上床下桌\n- 研究生：2人间，独立卫浴\n\n### 生活设施\n- 每层配备洗衣房\n- 24小时热水供应\n- 免费WiFi覆盖\n\n### 作息时间\n- 熄灯时间：23:00\n- 开门时间：6:00\n- 周末延长至24:00\n\n### 注意事项\n- 禁止使用大功率电器\n- 保持宿舍卫生\n- 遵守作息时间");
        guide2.setCategory(Guide.GuideCategory.LIFE);
        guide2.setTags(objectMapper.writeValueAsString(tags2));
        guide2.setStatus(Guide.GuideStatus.PUBLISHED);
        guide2.setPriority(8);
        guide2.setAuthor(teacher);
        guide2.setViewCount(234);
        guide2.setLikeCount(45);
        guideRepository.save(guide2);
        
        List<String> tags3 = Arrays.asList("选课", "学习", "课程", "学分");
        Guide guide3 = new Guide();
        guide3.setTitle("选课系统使用指南");
        guide3.setSummary("详细说明选课系统的使用方法，选课策略和注意事项。");
        guide3.setContent("## 选课系统使用指南\n\n### 选课时间\n- 第一轮选课：开学前一周\n- 第二轮选课：开学第一周\n- 补退选：开学第二周\n\n### 选课策略\n1. 优先选择必修课\n2. 合理安排课程时间\n3. 关注课程评价\n\n### 系统操作\n1. 登录教务系统\n2. 进入选课模块\n3. 查看课程信息\n4. 提交选课申请\n\n### 注意事项\n- 注意选课时间限制\n- 确保学分要求\n- 避免时间冲突");
        guide3.setCategory(Guide.GuideCategory.ACADEMIC);
        guide3.setTags(objectMapper.writeValueAsString(tags3));
        guide3.setStatus(Guide.GuideStatus.PUBLISHED);
        guide3.setPriority(9);
        guide3.setAuthor(teacher);
        guide3.setViewCount(189);
        guide3.setLikeCount(31);
        guideRepository.save(guide3);
    }
    
    private void initializeCampusServices() throws Exception {
        // 餐饮服务
        CampusService dining1 = new CampusService();
        dining1.setName("第一食堂");
        dining1.setDescription("学校主要餐厅，提供各种中式菜肴，价格实惠，环境舒适。");
        dining1.setCategory(CampusService.ServiceCategory.DINING);
        dining1.setLocation("学生活动中心一楼");
        dining1.setWorkingHours("06:30-21:30");
        dining1.setContactInfo("0571-12345678");
        dining1.setIcon("restaurant");
        dining1.setRating(4.5);
        dining1.setRatingCount(234);
        dining1.setStatus(CampusService.ServiceStatus.ACTIVE);
        dining1.setPriority(10);
        campusServiceRepository.save(dining1);
        
        CampusService dining2 = new CampusService();
        dining2.setName("清真餐厅");
        dining2.setDescription("专门为少数民族学生提供清真餐饮服务。");
        dining2.setCategory(CampusService.ServiceCategory.DINING);
        dining2.setLocation("第二食堂二楼");
        dining2.setWorkingHours("11:00-14:00, 17:00-20:00");
        dining2.setContactInfo("0571-12345679");
        dining2.setIcon("restaurant");
        dining2.setRating(4.3);
        dining2.setRatingCount(89);
        dining2.setStatus(CampusService.ServiceStatus.ACTIVE);
        dining2.setPriority(7);
        campusServiceRepository.save(dining2);
        
        // 图书馆服务
        CampusService library = new CampusService();
        library.setName("中心图书馆");
        library.setDescription("学校主图书馆，藏书丰富，提供自习室、电子阅览室等服务。");
        library.setCategory(CampusService.ServiceCategory.LIBRARY);
        library.setLocation("图书馆大楼");
        library.setWorkingHours("07:00-22:00");
        library.setContactInfo("0571-12345680");
        library.setIcon("library_books");
        library.setRating(4.8);
        library.setRatingCount(456);
        library.setStatus(CampusService.ServiceStatus.ACTIVE);
        library.setPriority(10);
        library.setIsOnline(true);
        library.setUrl("http://library.university.edu");
        campusServiceRepository.save(library);
        
        // 医疗服务
        CampusService medical = new CampusService();
        medical.setName("校医院");
        medical.setDescription("为师生提供基本医疗服务，包括常见疾病诊治、健康咨询等。");
        medical.setCategory(CampusService.ServiceCategory.MEDICAL);
        medical.setLocation("校医院大楼");
        medical.setWorkingHours("08:00-17:30");
        medical.setContactInfo("0571-12345681");
        medical.setIcon("local_hospital");
        medical.setRating(4.2);
        medical.setRatingCount(123);
        medical.setStatus(CampusService.ServiceStatus.ACTIVE);
        medical.setPriority(9);
        campusServiceRepository.save(medical);
        
        // 体育服务
        CampusService sports = new CampusService();
        sports.setName("体育中心");
        sports.setDescription("提供篮球、羽毛球、游泳等各种体育设施和课程。");
        sports.setCategory(CampusService.ServiceCategory.SPORTS);
        sports.setLocation("体育中心");
        sports.setWorkingHours("06:00-22:00");
        sports.setContactInfo("0571-12345682");
        sports.setIcon("sports");
        sports.setRating(4.6);
        sports.setRatingCount(345);
        sports.setStatus(CampusService.ServiceStatus.ACTIVE);
        sports.setPriority(8);
        campusServiceRepository.save(sports);
        
        // 交通服务
        CampusService transport = new CampusService();
        transport.setName("校园巴士");
        transport.setDescription("连接各个校区和主要建筑的免费巴士服务。");
        transport.setCategory(CampusService.ServiceCategory.TRANSPORTATION);
        transport.setLocation("各校区巴士站");
        transport.setWorkingHours("07:00-18:00");
        transport.setContactInfo("0571-12345683");
        transport.setIcon("directions_bus");
        transport.setRating(4.1);
        transport.setRatingCount(167);
        transport.setStatus(CampusService.ServiceStatus.ACTIVE);
        transport.setPriority(6);
        campusServiceRepository.save(transport);
    }
    
    private void initializePosts(User student, User teacher) throws Exception {
        List<String> tags1 = Arrays.asList("新生", "交流", "经验");
        Post post1 = new Post();
        post1.setTitle("新生报到经验分享");
        post1.setContent("刚刚完成报到，分享一些经验给后面的学弟学妹们：\n\n1. 提前准备好所有材料\n2. 可以网上预约报到时间\n3. 建议早点到，避免排队\n4. 记得带好银行卡缴费\n\n有什么问题可以问我！");
        post1.setCategory(Post.PostCategory.SHARING);
        post1.setTags(objectMapper.writeValueAsString(tags1));
        post1.setAuthor(student);
        post1.setStatus(Post.PostStatus.PUBLISHED);
        post1.setViewCount(89);
        post1.setLikeCount(12);
        post1.setCommentCount(5);
        postRepository.save(post1);
        
        List<String> tags2 = Arrays.asList("选课", "求助", "建议");
        Post post2 = new Post();
        post2.setTitle("选课求助！求推荐好的选修课");
        post2.setContent("大家好，我是大二学生，想请教一下有什么好的选修课推荐吗？\n\n希望是：\n- 不太难的\n- 老师好说话的\n- 最好有实用性的\n\n谢谢大家！");
        post2.setCategory(Post.PostCategory.QUESTION);
        post2.setTags(objectMapper.writeValueAsString(tags2));
        post2.setAuthor(student);
        post2.setStatus(Post.PostStatus.PUBLISHED);
        post2.setViewCount(156);
        post2.setLikeCount(8);
        post2.setCommentCount(15);
        postRepository.save(post2);
        
        List<String> tags3 = Arrays.asList("活动", "社团", "招新");
        Post post3 = new Post();
        post3.setTitle("计算机协会招新啦！");
        post3.setContent("🎉 计算机协会2024年招新开始啦！\n\n我们是一个专注于技术交流和实践的社团，主要活动包括：\n- 技术讲座和workshop\n- 编程竞赛\n- 项目开发\n- 企业参观\n\n欢迎对编程和技术感兴趣的同学加入我们！\n\n报名方式：扫描二维码或联系微信群");
        post3.setCategory(Post.PostCategory.ACTIVITY);
        post3.setTags(objectMapper.writeValueAsString(tags3));
        post3.setAuthor(teacher);
        post3.setStatus(Post.PostStatus.PUBLISHED);
        post3.setIsPinned(true);
        post3.setViewCount(298);
        post3.setLikeCount(45);
        post3.setCommentCount(23);
        postRepository.save(post3);
    }
    
    private void initializeQABot() {
        // 入学相关问答
        QABot qa1 = new QABot();
        qa1.setQuestion("新生报到需要带什么材料？");
        qa1.setAnswer("新生报到需要携带以下材料：\n1. 录取通知书原件\n2. 身份证原件及复印件\n3. 户口迁移证（如需迁户口）\n4. 党团关系转移材料\n5. 一寸免冠照片若干张\n6. 体检报告\n7. 银行卡（用于缴费）\n建议提前准备好所有材料，避免报到当天遗漏。");
        qa1.setKeywords("报到,材料,证件,录取通知书,身份证");
        qa1.setCategory(QABot.QACategory.ADMISSION);
        qa1.setPriority(10);
        qaBotRepository.save(qa1);
        
        QABot qa2 = new QABot();
        qa2.setQuestion("宿舍什么时候可以入住？");
        qa2.setAnswer("宿舍入住时间安排如下：\n- 本科生：9月1日-9月2日\n- 研究生：8月30日-8月31日\n\n入住流程：\n1. 先完成报到手续\n2. 到宿管中心领取钥匙\n3. 到指定宿舍楼办理入住\n4. 检查宿舍设施并签字确认\n\n注意：请在规定时间内入住，如有特殊情况请提前联系宿管中心。");
        qa2.setKeywords("宿舍,入住,时间,钥匙,住宿");
        qa2.setCategory(QABot.QACategory.LIFE);
        qa2.setPriority(9);
        qaBotRepository.save(qa2);
        
        // 学习相关问答
        QABot qa3 = new QABot();
        qa3.setQuestion("如何使用选课系统？");
        qa3.setAnswer("选课系统使用步骤：\n1. 登录教务管理系统\n2. 进入\"选课管理\"模块\n3. 查看可选课程列表\n4. 点击\"选课\"按钮添加课程\n5. 确认选课信息无误后提交\n\n选课时间：\n- 第一轮：开学前一周\n- 第二轮：开学第一周\n- 补退选：开学第二周\n\n注意事项：请注意课程时间冲突，确保满足学分要求。");
        qa3.setKeywords("选课,系统,教务,课程,学分");
        qa3.setCategory(QABot.QACategory.ACADEMIC);
        qa3.setPriority(8);
        qaBotRepository.save(qa3);
        
        QABot qa4 = new QABot();
        qa4.setQuestion("图书馆开放时间是什么时候？");
        qa4.setAnswer("图书馆开放时间：\n- 周一至周日：7:00-22:00\n- 节假日：8:00-20:00\n\n各楼层功能：\n- 1楼：总服务台、报刊阅览区\n- 2楼：社科图书借阅区\n- 3楼：自然科学图书借阅区\n- 4楼：电子阅览室、研讨室\n- 5楼：自习室\n\n借书规则：本科生可借20册，研究生可借30册，借期30天，可续借一次。");
        qa4.setKeywords("图书馆,开放时间,借书,自习,阅览");
        qa4.setCategory(QABot.QACategory.SERVICE);
        qa4.setPriority(7);
        qaBotRepository.save(qa4);
        
        // 生活服务相关
        QABot qa5 = new QABot();
        qa5.setQuestion("食堂饭卡怎么充值？");
        qa5.setAnswer("校园一卡通充值方式：\n1. 食堂内充值机充值（支持现金）\n2. 手机APP充值：\n   - 下载\"校园一卡通\"APP\n   - 绑定卡号和身份信息\n   - 选择充值金额并支付\n3. 网上充值：登录校园卡管理系统\n4. 银行代扣：到指定银行办理自动充值业务\n\n充值到账时间：实时到账\n充值限额：单次最高500元，日限额1000元");
        qa5.setKeywords("饭卡,充值,一卡通,食堂,支付");
        qa5.setCategory(QABot.QACategory.LIFE);
        qa5.setPriority(6);
        qaBotRepository.save(qa5);
        
        QABot qa6 = new QABot();
        qa6.setQuestion("校园网怎么连接？");
        qa6.setAnswer("校园网连接方法：\n\n无线网络：\n1. 搜索WiFi：\"CampusNet\"\n2. 输入学号和密码登录\n3. 首次使用需要激活账号\n\n有线网络：\n1. 连接网线到电脑\n2. 打开浏览器，会自动跳转到认证页面\n3. 输入学号和密码\n\n网络问题解决：\n- 密码错误：到网络中心重置密码\n- 无法连接：检查网络设置或联系技术支持\n- 网速慢：避开网络高峰期（19:00-23:00）");
        qa6.setKeywords("校园网,WiFi,网络,连接,密码");
        qa6.setCategory(QABot.QACategory.TECHNOLOGY);
        qa6.setPriority(5);
        qaBotRepository.save(qa6);
        
        QABot qa7 = new QABot();
        qa7.setQuestion("如何申请助学金？");
        qa7.setAnswer("助学金申请流程：\n\n申请条件：\n- 家庭经济困难学生\n- 品学兼优，无违纪记录\n- 积极参与学校活动\n\n申请材料：\n1. 助学金申请表\n2. 家庭经济情况调查表\n3. 家庭收入证明\n4. 学习成绩单\n5. 个人陈述\n\n申请时间：每年9月-10月\n申请流程：个人申请→班级评议→学院审核→学校审批\n\n资助标准：一等4000元/年，二等3000元/年，三等2000元/年");
        qa7.setKeywords("助学金,申请,资助,经济困难,奖学金");
        qa7.setCategory(QABot.QACategory.SERVICE);
        qa7.setPriority(4);
        qaBotRepository.save(qa7);
    }
}