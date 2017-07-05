$(function () {
    /**跳转到添加用户界面*/
    $("#addUser").on('click',function () {
       $.ajax({
           url:"/system/userManage/toAddUser",
           type: "POST"
        }).done(function(data) {
            layer.open({
                type: 1,
                shade: [0.6, '#AAAAAA'],
                area: ["450px", "500px"],
                title: "添加用户",
                content: data,
                btn:['确定','取消'],
                yes:function () {
                    $.ajax({
                        url:"/system/userManage/saveUser",
                        datatype:"json",
                        type:"post",
                        data:$("#addUserForm").serialize(),
                        success:function (data) {
                            if(data.error != null && data.error != ""){
                                layer.msg(data.error,{icon: 5});
                            }else{
                                window.top.layer.msg("保存成功",{icon: 1});
                                window.location.reload();
                            }
                        }
                    })
                },
                btn2:function () {

                }
            });
        });
    });

});

/**激活、冻结用户*/
function activativeAccount(obj){
    var userid = $(obj).attr('value');
    var availableCode = $(obj).attr('available');
    if("未激活" === availableCode){ //未激活,需要激活
         if(isEmpty(userid)){
              layer.msg("激活失败!用户id不能为空",{icon:5});
              return false;
         }
         window.location.href="/system/userManage/activativeAccount?userid="+userid+"&availableCode="+availableCode;
    }else{ //已经激活,需要冻结
         if(isEmpty(userid)){
               layer.msg("冻结失败!用户id不能为空",{icon:5});
               return false;
         }
         window.location.href="/system/userManage/activativeAccount?userid="+userid+"&availableCode="+availableCode;
    }

}