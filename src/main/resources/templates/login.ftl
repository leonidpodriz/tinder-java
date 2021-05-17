<#include "base.ftl">
<#macro page_head>
    <title>Login</title>
</#macro>

<#macro page_body>
    <form class="form-signin text-center">
        <img class="mb-5" src="https://www.robo.house/wp-content/uploads/2017/10/dan-shaded.png" width="150" alt="" >
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; Tinder 2021</p>
    </form>

</#macro>
<@display_page/>