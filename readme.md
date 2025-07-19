# Mahjong Bot - QQ 麻将比赛 Elo 评分系统机器人

本项目是一个基于 [Shiro](https://github.com/MisakaTAT/Shiro) 框架开发的 QQ 机器人插件，专为麻将类比赛提供自动化记分、Elo 评分计算与排名更新功能。适用于多人麻将对战群组，支持自动结算比赛结果、更新 Elo 排名，并在群内发送结构化比赛信息。

---

## 📌 功能概览

### ✅ 核心功能
- **比赛创建**：支持通过指令 `创建比赛` 创建一场新的麻将比赛。
- **比赛记录添加**：通过 `添加记录 <比赛ID> <方向> <分数>` 添加每位玩家的比赛得分。
- **自动结算**：当所有玩家提交完分数后，自动进行精算并结束比赛。
- **Elo 评分更新**：根据比赛结果使用 Elo 算法动态调整玩家评分。
- **消息推送**：比赛结束后自动向群组发送结构化比赛结果与 Elo 变动情况。
- **数据持久化**：使用 MyBatis + MySQL 存储比赛记录、Elo 历史等信息。

---

## 🧩 技术栈

| 技术 | 描述 |
|------|------|
| Java 17+ | 主语言 |
| Spring Boot | 后端框架 |
| Shiro | QQ 机器人框架 |
| MyBatis Plus | ORM 数据库操作 |
| MySQL | 数据存储 |
| Lombok | 自动生成 Getter/Setter |
| Markdown/MsgUtils | 消息格式化输出 |

---

## 📁 项目结构

```
mahjong-bot/
├── src/
│   ├── main/
│   │   ├── java/org/liahnu/bot/
│   │   │   ├── config/         # 配置类（如 BotConfig）
│   │   │   ├── model/          # 数据模型（Contest, ContestRecord, Elo）
│   │   │   ├── plugin/         # Shiro 插件类（处理群聊指令）
│   │   │   ├── service/        # 业务逻辑接口及实现
│   │   │   ├── util/           # 工具类（Elo 计算、点数规则等）
│   │   │   └── Main.java       # Spring Boot 启动类
│   │   └── resources/
│   │       ├── application.yaml # 配置文件（数据库、机器人账号等）
│   │       └── mapper/         # MyBatis XML 映射文件
│   └── test/                   # 单元测试
├── build.gradle.kts             # 构建配置
├── gradlew / gradlew.bat        # Gradle Wrapper
└── README.md                    # 项目说明文档
```


---

## 🛠️ 功能模块介绍

### 1. 比赛管理模块 (`ContestPlugin`)
- 创建比赛 `创建比赛`
- 查询比赛 `查询比赛`
- 支持 RCR、MLeague 等多种麻将规则

### 2. 比赛记录模块 (`ContestRecordPlugin`)
- 添加比赛记录 `添加记录 <比赛ID> <方向> <分数>`
- 自动触发 Elo 更新与比赛状态变更

### 3. Elo 评分模块 (`elo`)
- 使用 Elo 算法动态更新玩家评分
- 支持不同麻将类型独立评分（如 RCR、MLeague）

### 4. 点数计算模块 ([point](file://C:\Users\li hanyu\IdeaProjects\mahjong-bot\src\main\java\org\liahnu\bot\model\domain\ContestRecord.java#L44-L45))
- 支持多种麻将规则（RCR、MLeague 等）的点数结算
- 提供 [RuleCalculate.calculate(...)](file://C:\Users\li hanyu\IdeaProjects\mahjong-bot\src\main\java\org\liahnu\bot\util\point\RuleCalculate.java#L18-L18) 方法进行点数转换

---

## 🧪 示例流程

### 创建比赛：
```text
@bot 创建比赛
```


### 添加记录：
```text
@bot 添加记录 1001 东 50
@bot 添加记录 1001 南 10
@bot 添加记录 1001 西 -10
@bot 添加记录 1001 北 -40
```


### 比赛结束：
当所有玩家提交完毕，系统自动结算并推送如下消息：

```
🏆 比赛结束！以下是 Elo 变动情况：
------------------------------
👤 用户 ID: 123456789
📈 Elo: 2000 → 2015 (+15)
------------------------------
👤 用户 ID: 987654321
📈 Elo: 2100 → 2085 (-15)
------------------------------
```


---

## 🚀 快速开始

### 1. 安装依赖

确保你已安装以下环境：

- Java 17+
- Maven 或 Gradle
- MySQL 5.7+

### 2. 初始化数据库

导入 SQL 表结构,见 /doc/table.sql


### 3. 修改配置文件

编辑 `application.yaml`：

```yaml
shiro:
  bots:
    - qq: 3542130180
      enable: true

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mahjong_bot?useSSL=false&serverTimezone=UTC
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
```


### 4. 编译运行

```bash
./gradlew bootRun
```


---

## 📬 命令列表

| 命令 | 参数                 | 说明                     |
|------|--------------------|------------------------|
| 创建比赛 | 无                  | 创建一场新比赛（默认 RCR 规则）     |
| 查询比赛 | 无                  | 查看当前群组最近的比赛信息          |
| 添加记录 | `<比赛ID> <座位> <分数>` | 添加单局比赛成绩，自动触发 Elo 更新   |
| 查看排名 | 无                  | 查看当前 Elo 排名榜（需自行实现）    |
| 更新比赛 | `<比赛ID>`           | 手动触发 Elo 结算（仅限私聊） 用于测试 |

---

## 📊 Elo 系统说明

### Elo 公式参考：

```java
expectedScore = 1 / (1 + Math.pow(10, (opponentRating - playerRating) / 400));
newRating = currentRating + kFactor * (actualScore - expectedScore);
```


### K 因子计算方式：

```java
kFactor = 16 * (1 + Math.abs(ratingDifference) / 400);
```


---

## 🧪 测试建议

- 单元测试使用 JUnit 5，覆盖 Elo 计算、点数转换、比赛状态流转等核心逻辑。
- 可使用 H2 内存数据库进行集成测试。
- 建议编写模拟 Bot 发送消息的测试用例，验证消息格式是否正确。

---

## 🤝 开发贡献

欢迎 Fork & PR！

你可以参与的方向包括：

- 支持更多麻将规则（如国标、立直A,R等规则、广东麻将等）
- 实现 Elo 的 Glicko-2 替代算法
- 添加用户昵称绑定、排行榜展示、历史战绩查询等功能
- 支持 Markdown 格式消息输出

---

## 📜 License

MIT License

---