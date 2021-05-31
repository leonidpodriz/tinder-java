<#include "base.ftl">
<#macro page_head>
    <title>People list</title>
</#macro>

<#macro page_body>
    <div class="container">
        <div class="row">
            <div class="col-8 offset-2">
                <div class="panel panel-default user_panel">
                    <div class="panel-heading">
                        <h3 class="panel-title">User List</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-container">
                            <table class="table-users table" border="0">
                                <tbody>
                                <#list users as user>
                                    <tr>
                                        <td width="10">
                                            <a href="/messages/?id=${user.ID}">
                                                <div class="avatar-img">
                                                    <img class="img-circle"
                                                         src="${user.AVATAR_URI}" alt="avatar">
                                                </div>
                                            </a>
                                        </td>
                                        <td class="align-middle">
                                            <a style="color: inherit; text-decoration: none;" class=".stretched-link"
                                               href="/chat/?id=${user.ID}">
                                                ${user.NAME}
                                            </a>
                                        </td>
                                        <td class="align-middle">
                                            Builder Sales Agent
                                        </td>
                                        <td class="align-middle">
                                            Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>
                                        </td>
                                    </tr>
                                <#else>
                                    <p>No users</p>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</#macro>
<@display_page/>