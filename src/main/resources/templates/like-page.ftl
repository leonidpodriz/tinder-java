<#include "base.ftl">
<#macro page_head>
    <title>Like Page</title>
</#macro>

<#macro page_body>

    <div class="col-4 offset-4">
        <form class="card" method="post">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="${user_avatar}" alt=""
                             class="mx-auto rounded-circle img-fluid">
                        <h3 class="mb-0 text-truncated">${user_name}</h3>
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button type="submit" class="btn btn-outline-danger btn-block" value="false" name="like"><span
                                    class="fa fa-times"></span> Dislike
                        </button>
                    </div>
                    <input type="hidden" value="${id}" name="id">
                    <div class="col-12 col-lg-6">
                        <button type="submit" class="btn btn-outline-success btn-block" value="true" name="like"><span class="fa fa-heart"></span> Like
                        </button>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
            </div>
            <!--/card-block-->
        </form>
    </div>


</#macro>
<@display_page/>