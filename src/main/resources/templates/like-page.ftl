<#include "base.ftl">
<#macro page_head>
    <title>Like Page</title>
</#macro>

<#macro page_body>

    <div class="col-4 offset-4">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="https://robohash.org/68.186.255.198.png" alt=""
                             class="mx-auto rounded-circle img-fluid">
                        <h3 class="mb-0 text-truncated">User name</h3>
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button type="button" class="btn btn-outline-danger btn-block"><span
                                    class="fa fa-times"></span> Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like
                        </button>
                    </div>
                    <!--/col-->
                </div>
                <!--/row-->
            </div>
            <!--/card-block-->
        </div>
    </div>


</#macro>
<@display_page/>