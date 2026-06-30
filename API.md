# 日记小程序 - 后端接口清单

## 目录

- [一、用户认证模块](#一用户认证模块)
- [二、日记管理模块](#二日记管理模块)
- [三、标签管理模块](#三标签管理模块)
- [四、数据统计模块](#四数据统计模块)
- [五、AI 分析模块](#五ai-分析模块)
- [六、文件上传模块](#六文件上传模块)
- [七、导出模块](#七导出模块)
- [八、设置模块](#八设置模块)
- [数据库表设计参考](#数据库表设计参考)

---

## 通用说明

### 认证方式

所有接口（除登录外）请求头携带 Token：

```
Authorization: Bearer <jwt_token>
```

### 统一响应格式

```json
{
  "code": 0,
  "message": "success",
  "data": {}
}
```

### 分页列表格式

```json
{
  "code": 0,
  "data": {
    "list": [],
    "total": 100,
    "page": 1,
    "pageSize": 20
  }
}
```

---

## 一、用户认证模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 微信登录 | `POST` | `/api/auth/wx-login` | 微信 code 换取登录态，返回 token |
| 获取用户信息 | `GET` | `/api/user/info` | 获取当前登录用户信息 |
| 更新用户信息 | `PUT` | `/api/user/info` | 更新昵称、头像等 |

### 微信登录

**请求：**

```json
{
  "code": "wx_login_code"
}
```

**响应：**

```json
{
  "token": "jwt_token_string",
  "user": {
    "id": 1,
    "openid": "wx_openid",
    "nickname": "每日日记",
    "avatar": "https://...",
    "createdAt": "2024-01-01T00:00:00Z"
  }
}
```

---

## 二、日记管理模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 创建日记 | `POST` | `/api/diaries` | 新建一篇日记 |
| 获取日记详情 | `GET` | `/api/diaries/:id` | 获取单篇日记详情 |
| 更新日记 | `PUT` | `/api/diaries/:id` | 更新日记内容 |
| 删除日记 | `DELETE` | `/api/diaries/:id` | 删除一篇日记 |
| 日记列表（分页） | `GET` | `/api/diaries` | 分页获取日记列表 |
| 高级筛选日记 | `GET` | `/api/diaries/filter` | 多维度筛选（类型/心情/标签/日期） |
| 搜索日记 | `GET` | `/api/diaries/search` | 关键词搜索日记 |
| 按日期获取日记 | `GET` | `/api/diaries/by-date` | 获取指定日期的日记 |
| 按月获取日记 | `GET` | `/api/diaries/by-month` | 获取指定月份的日记（日历页用） |

### 创建日记

**请求：**

```json
{
  "title": "今天的心情",
  "content": "今天天气很好...",
  "mood": "happy",
  "tags": ["生活", "随笔"],
  "images": ["https://.../1.jpg", "https://.../2.jpg"],
  "voice": "https://.../voice.mp3",
  "voiceDuration": 30,
  "video": "https://.../video.mp4",
  "videoThumb": "https://.../thumb.jpg"
}
```

**响应：**

```json
{
  "id": "diary_123",
  "title": "今天的心情",
  "content": "今天天气很好...",
  "mood": "happy",
  "tags": ["生活", "随笔"],
  "type": "mixed",
  "images": [],
  "voice": "",
  "voiceDuration": 30,
  "video": "",
  "createdAt": "2024-01-15T10:30:00Z",
  "updatedAt": "2024-01-15T10:30:00Z"
}
```

### 高级筛选

```
GET /api/diaries/filter?keyword=旅行&type=image&mood=happy&tag=生活&startDate=2024-01-01&endDate=2024-06-30&page=1&pageSize=20
```

**筛选参数：**

| 参数 | 类型 | 说明 |
|---|---|---|
| `keyword` | string | 关键词（匹配标题/内容/标签） |
| `type` | string | 日记类型：text / voice / image / video |
| `mood` | string | 心情：happy / excited / calm / sad / angry / tired / love / surprised |
| `tag` | string | 标签名 |
| `startDate` | string | 开始日期，格式 YYYY-MM-DD |
| `endDate` | string | 结束日期，格式 YYYY-MM-DD |
| `page` | number | 页码，默认 1 |
| `pageSize` | number | 每页数量，默认 20 |

---

## 三、标签管理模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 获取所有标签 | `GET` | `/api/tags` | 获取所有标签及对应日记数量 |
| 获取标签下的日记 | `GET` | `/api/tags/:name/diaries` | 获取指定标签下的日记列表 |
| 重命名标签 | `PUT` | `/api/tags/:name` | 重命名标签（批量更新所有日记中的标签名） |
| 删除标签 | `DELETE` | `/api/tags/:name` | 删除标签（从所有日记中移除该标签） |

### 获取所有标签

**响应：**

```json
[
  { "name": "生活", "count": 15 },
  { "name": "工作", "count": 8 },
  { "name": "旅行", "count": 5 }
]
```

### 重命名标签

**请求：**

```json
{
  "newName": "日常生活"
}
```

---

## 四、数据统计模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 获取统计数据 | `GET` | `/api/stats/overview` | 首页顶部统计（总篇数/本月/连续天数/类型分布） |

### 获取统计数据

**响应：**

```json
{
  "total": 128,
  "thisMonth": 12,
  "consecutiveDays": 7,
  "types": {
    "text": 80,
    "voice": 15,
    "image": 20,
    "video": 5,
    "mixed": 8
  }
}
```

---

## 五、AI 分析模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 获取 AI 模式列表 | `GET` | `/api/ai/modes` | 获取可用的 AI 分析模式列表 |
| AI 分析日记 | `POST` | `/api/ai/analyze` | 对日记进行 AI 分析 |
| AI 对话历史 | `GET` | `/api/ai/history/:diaryId` | 获取某篇日记的 AI 分析历史 |

### AI 分析日记

**请求：**

```json
{
  "diaryId": "diary_123",
  "mode": "wang_yangming",
  "content": "今天工作很累，但还是坚持完成了任务...",
  "mood": "tired"
}
```

**响应（推荐 SSE 流式返回）：**

```json
{
  "mode": {
    "key": "wang_yangming",
    "name": "王阳明心学模式",
    "icon": "📜"
  },
  "analysis": "从王阳明心学的角度来看...",
  "timestamp": 1700000000000
}
```

> 💡 建议使用 SSE（Server-Sent Events）流式返回，用户体验更好。

---

## 六、文件上传模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 上传图片 | `POST` | `/api/upload/image` | 上传日记图片（支持批量） |
| 上传语音 | `POST` | `/api/upload/voice` | 上传语音文件（mp3） |
| 上传视频 | `POST` | `/api/upload/video` | 上传视频文件 |
| 获取上传凭证 | `GET` | `/api/upload/token` | 获取 OSS/COS 直传凭证（可选） |

**推荐方案：** 后端生成 OSS/COS 临时签名，前端直传到对象存储，减少服务器带宽压力。

---

## 七、导出模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 导出数据 | `POST` | `/api/export` | 导出所有日记数据（支持 JSON / Markdown 格式） |

### 导出数据

**请求：**

```json
{
  "format": "markdown"
}
```

**响应：**

```json
{
  "downloadUrl": "https://.../diary-export-xxx.md",
  "filename": "my-diary-20240630.md"
}
```

---

## 八、设置模块

| 接口 | 方法 | 路径 | 描述 |
|---|---|---|---|
| 获取用户设置 | `GET` | `/api/settings` | 获取用户偏好设置（主题等） |
| 更新用户设置 | `PUT` | `/api/settings` | 更新用户偏好设置 |

### 设置数据结构

```json
{
  "theme": "dark",
  "defaultMood": "happy",
  "reminderEnabled": true,
  "reminderTime": "21:00"
}
```

---

## 数据库表设计参考

### users 用户表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `openid` | varchar(64) | 微信 openid |
| `unionid` | varchar(64) | 微信 unionid（可选） |
| `nickname` | varchar(64) | 昵称 |
| `avatar` | varchar(255) | 头像 URL |
| `created_at` | datetime | 创建时间 |
| `updated_at` | datetime | 更新时间 |

### diaries 日记表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `user_id` | bigint | 用户 ID |
| `title` | varchar(255) | 标题 |
| `content` | text | 正文内容 |
| `mood` | varchar(20) | 心情 |
| `type` | varchar(20) | 类型：text / voice / image / video / mixed |
| `voice` | varchar(255) | 语音文件 URL |
| `voice_duration` | int | 语音时长（秒） |
| `video` | varchar(255) | 视频文件 URL |
| `video_thumb` | varchar(255) | 视频缩略图 URL |
| `created_at` | datetime | 创建时间 |
| `updated_at` | datetime | 更新时间 |
| `deleted_at` | datetime | 软删除时间 |

### diary_images 日记图片表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `diary_id` | bigint | 日记 ID |
| `url` | varchar(255) | 图片 URL |
| `sort_order` | int | 排序 |

### diary_tags 日记标签关联表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `diary_id` | bigint | 日记 ID |
| `tag_name` | varchar(50) | 标签名 |

### tags 标签表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `user_id` | bigint | 用户 ID |
| `name` | varchar(50) | 标签名 |
| `created_at` | datetime | 创建时间 |

### ai_analyses AI 分析记录表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `diary_id` | bigint | 日记 ID |
| `mode` | varchar(50) | 分析模式 |
| `content` | text | 分析结果 |
| `created_at` | datetime | 创建时间 |

### user_settings 用户设置表

| 字段 | 类型 | 说明 |
|---|---|---|
| `id` | bigint | 主键 |
| `user_id` | bigint | 用户 ID |
| `theme` | varchar(20) | 主题：light / dark |
| `reminder_enabled` | tinyint | 是否开启提醒 |
| `reminder_time` | varchar(10) | 提醒时间，格式 HH:mm |
| `updated_at` | datetime | 更新时间 |

---

## 接口优先级

### P0（核心，必做）
- 用户认证（微信登录）
- 日记 CRUD
- 标签管理
- 文件上传

### P1（重要）
- 筛选搜索
- 数据统计
- 数据导出

### P2（增值功能）
- AI 分析
- 用户设置
- 消息提醒
