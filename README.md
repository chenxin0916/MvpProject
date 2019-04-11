# 笔记
PermissionsDispatcher ---->>运行时权限处理

@RuntimePermissions :注解在需要使用运行时权限的activity或者fragment上；
@NeedsPermission() :注解在获得了权限的方法上参数为据此的权限名称，多个权限用{}；
@OnShowRationale() :当请求权限第一次被拒绝时，第二次发送请求前调用其注解方法，用以说明为什么需要此权限；
@OnPermissionDenied() :当请求时被拒绝，没有带勾不再询问时会调用；
@OnNeverAskAgain() :拒绝并且勾选了不再询问时调用

以上注解的方法不能用private修饰

装个PermissionsDispatcher-pluge---贼好用


immersionbar------>>>各种栏（状态栏，actionbar，导航栏什么的）
immersionbar.init() 最简单的沉浸式状态栏实现


SelectorChapek for Android//自动生成selector插件，需要文件名后缀有一定规律

_normal	       (default state)
_pressed	     state_pressed                                                                                                   
_focused	     state_focused                                                                                                   
_disabled	     state_enabled (false)                                                                                          
_checked	     state_checked                                                                                                  
_selected	     state_selected                                                                                                 
_hovered	     state_hovered                                                                                                  
_checkable	   state_checkable                                                                                                
_activated	   state_activated                                                                                               
_windowfocused	state_window_focused                                                                                           
