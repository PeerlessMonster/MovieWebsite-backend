# MovieWebsite
一个十分简易的电影信息网站  
v0.9——[dev分支20240219](https://github.com/PeerlessMonster/MovieWebsite-backend/tree/5043ecb2f022c9d01fcbf8dbf34f88dc66bbb919)

此仓库为后端部分，去往[前端部分](https://github.com/PeerlessMonster/MovieWebsite-frontend)

（关键词）***前后端分离 Session***



## 有啥功能🧐
- 电影信息
> - 轮播图，缓存
> - 最新上映：按上映时间降序，展示前6个结果，缓存
> - 广受好评：按评分降序，展示前10个结果，缓存
> - 时下热门：按播放量降序，展示前7个结果，缓存
> - 搜索：按分类、地区筛选，按名称/导演/编剧/主演搜索，按上映时间/时长/播放量/评分排降序/升序，供前端分页
- 账号管理
> - 供前端应用启动时获取session，勾选“7天内免登录”照常减少过期时间；未勾选重置过期时间
> - 登录账号：缓存session，勾选“7天内免登录”设置过期时间7天；未勾选设置30分钟
> - 注册账号
> - 升级VIP
> - 修改昵称、邮箱
> - 修改密码，需要先验证旧密码
- 其他……
> - RESTful API



## 大感谢🫡
### 依赖
- 框架：[Spring Boot](https://spring.io/projects/spring-boot)
- ORM：[MyBatis-Plus](https://baomidou.com/)
- 开发工具：[Lombok](https://projectlombok.org/)
- 数据库：[MySQL](https://www.mysql.com/) 8.0
- 缓存：[Redis](https://redis.io/)

### 数据
- MovieWebsite_db.sql中，所有电影信息来自[豆瓣](https://www.douban.com/)

### 素材
- resources/static/images/movies下，1_m.webp（中号竖版海报）、1_l.webp（大号横板海报）……  
  所有影片的海报来自[豆瓣](https://www.douban.com/)，这里不提供

无商业用途！仅用作学习Web后端开发，编写代码最终展示效果