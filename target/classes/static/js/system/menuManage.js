$(function(){

    /**跳转添加菜单页面*/
    $(".addMenu").on('click',function () {
        $.ajax({
            url:"/system/MenuManageCtrl/toAddMenuPage",
            datatype:"json"
        }).done(function(content){
            layer.open({
                type: 1,
                title:"添加菜单",
                area:["500px","520px"],
                shade:[0.5, '#DDDDDD'],
                skin:'demo-class',
                content: content,
                scrollbar:false,
                btn:["保存","取消"],
                yes:function(){
                    validateNoNull($("#addMenuForm")[0]);
                },
                btn2:function () {

                }
            });
        })
    });
})