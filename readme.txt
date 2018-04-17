build.gradle文件管理jar包
schema/build.gradle文件配置数据库，执行run.sh脚本自动生成pojo
swagger/api配置controller层的接口，执行run.sh脚本自动生成相应jar包

注意：项目与swagger-codegen项目需要在同一目录下（原因请看swagger/run.sh脚本）
启动项目后访问localhost：8006/swagger-ui.html进入swagge-ui页面进行测试
