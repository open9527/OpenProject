 
 AucFrame  开始
 0.认识- export 提供对外api和bean , pkg 业务逻辑, app 独立运行模块
 
 1.准备工作 AndroidStudio 3.5 以上版本,创建一个项目
 2.创建 buildSrc,注意是文件夹方式创建 然后 创建.gitignore和build.gradle文件 sync一下 (项目中查看具体代码)
 3.在 buildSrc中创建src->main->Config.groovy , 然后就可以配置app 中的 build.gradle  (项目中查看具体代码)
 
 //rm -rf .idea
   find . -name "*.iml" -type f -exec rm -rf {} \;
   find . -name "build" -type d -exec rm -rf {} \;
