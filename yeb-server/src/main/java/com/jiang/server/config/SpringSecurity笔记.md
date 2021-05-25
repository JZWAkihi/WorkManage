

## SpringSecurity  

#### UserDetailsService

#### userDetails

#### SpringSecurity Config

#### PassWordEncoder

#### SecurityContextHolder
> SecurityContextHolder用于获取当前用户的信息，
> SecurityContextHolder默认使用ThreadLocal来存储认证信息，
>这是一种与线程绑定的策略。
>
>只要在同一个线程中进行，即使不在各个方法之间以参数的形式传递，
> 各个方法也能通过SecurityContextHolder工具获取到安全上下文。
~~~markdown

Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
    String username = ((UserDetails)principal).getUsername();
} else {
    String username = principal.toString();
}

~~~

#### UsernamePasswordAuthenticationToken

#### 注解使用
~~~markdown
ApiModel 
ApiModelProperty

~~~


