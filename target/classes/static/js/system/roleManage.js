$(function () {
    /**跳转到添加用户界面*/
    $("#addRole").on('click',function () {
        $.ajax({
            url:"/system/RoleManageCtrl/toAddRole",
            type: "POST"
        }).done(function(data) {
            layer.open({
                type: 1,
                shade: [0.6, '#AAAAAA'],
                area: ["450px", "500px"],
                title: "添加角色",
                content: data,
                btn:['确定','取消'],
                yes:function () {
                    //角色名称不能为空
                    var rolename = $('input[name=rolename]').val();
                    if(isEnpty(rolename)){
                        layer.msg("角色名称不能为空",{icon: 5});
                        return false;
                    }
                    $.ajax({
                        url:"/system/RoleManageCtrl/saveRole",
                        datatype:"json",
                        type:"post",
                        data:$("#addRoleForm").serialize(),
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

    /**删除角色*/
    $("#delRole").on("click",function () {
        layer.confirm('是否删除?', function(index){
            var roleid = this.value;
            if(isEnpty(roleid)){
                layer.msg("roleid不能为空",{icon: 5});
                return false;
            }
            window.location.href = "/system/RoleManageCtrl/delRole?roleid="+roleid;
        });
    })
});