# 日记小程序后端开发任务清单

## P0 核心功能（必做）

### 用户认证模块
- [ ] 实现微信登录接口 `POST /api/auth/wx-login`
  - [ ] 微信 code 换取 openid
  - [ ] 生成 JWT token
  - [ ] 用户信息注册/查询
- [ ] 实现获取用户信息接口 `GET /api/user/info`
- [ ] 实现更新用户信息接口 `PUT /api/user/info`

### 日记管理模块
- [ ] 实现创建日记接口 `POST /api/diaries`
  - [ ] 支持文字/语音/图片/视频/混合类型
  - [ ] 标签关联处理
  - [ ] 图片列表处理
- [ ] 实现获取日记详情接口 `GET /api/diaries/:id`
- [ ] 实现更新日记接口 `PUT /api/diaries/:id`
- [ ] 实现删除日记接口 `DELETE /api/diaries/:id`（软删除）
- [ ] 实现日记列表接口 `GET /api/diaries`（分页）
- [ ] 实现高级筛选接口 `GET /api/diaries/filter`
  - [ ] keyword 关键词搜索
  - [ ] type 日记类型筛选
  - [ ] mood 心情筛选
  - [ ] tag 标签筛选
  - [ ] startDate/endDate 日期范围筛选
- [ ] 实现搜索日记接口 `GET /api/diaries/search`
- [ ] 实现按日期获取日记接口 `GET /api/diaries/by-date`
- [ ] 实现按月获取日记接口 `GET /api/diaries/by-month`

### 标签管理模块
- [ ] 实现获取所有标签接口 `GET /api/tags`
- [ ] 实现获取标签下日记列表接口 `GET /api/tags/:name/diaries`
- [ ] 实现重命名标签接口 `PUT /api/tags/:name`
- [ ] 实现删除标签接口 `DELETE /api/tags/:name`

### 文件上传模块
- [ ] 实现上传图片接口 `POST /api/upload/image`（支持批量）
- [ ] 实现上传语音接口 `POST /api/upload/voice`
- [ ] 实现上传视频接口 `POST /api/upload/video`
- [ ] 实现获取上传凭证接口 `GET /api/upload/token`（可选 OSS/COS）

## P1 重要功能

### 筛选搜索功能
- [ ] 实现关键词搜索（匹配标题/内容/标签）
- [ ] 优化分页查询性能
- [ ] 添加索引优化

### 数据统计模块
- [ ] 实现统计数据接口 `GET /api/stats/overview`
  - [ ] 总日记篇数
  - [ ] 本月日记数
  - [ ] 连续记录天数
  - [ ] 类型分布统计

### 数据导出模块
- [ ] 实现导出接口 `POST /api/export`
  - [ ] 支持 JSON 格式
  - [ ] 支持 Markdown 格式

## P2 增值功能

### AI 分析模块
- [ ] 实现获取 AI 模式列表接口 `GET /api/ai/modes`
- [ ] 实现 AI 分析日记接口 `POST /api/ai/analyze`
  - [ ] 支持 SSE 流式返回
- [ ] 实现 AI 对话历史接口 `GET /api/ai/history/:diaryId`

### 用户设置模块
- [ ] 实现获取用户设置接口 `GET /api/settings`
- [ ] 实现更新用户设置接口 `PUT /api/settings`
  - [ ] 主题设置 (light/dark)
  - [ ] 默认心情设置
  - [ ] 提醒设置

### 其他
- [ ] JWT 认证拦截器
- [ ] 全局异常处理
- [ ] 接口文档生成 (Swagger)
- [ ] 日志配置
- [ ] 单元测试

## 数据库相关
- [ ] Flyway 迁移脚本编写
- [ ] 数据库索引优化
- [ ] 数据库连接池配置
