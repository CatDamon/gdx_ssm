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
                    if(isEmpty(rolename)){
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

    /**跳转到修改角色的界面*/
     $("#editRole").on('click',function () {
            var roleid = $('#editRole').attr("value");
            $.ajax({
                url:"/system/RoleManageCtrl/toEditRole",
                type: "POST"
            }).done(function(data) {
                layer.open({
                    type: 1,
                    shade: [0.6, '#AAAAAA'],
                    area: ["450px", "500px"],
                    title: "修改角色",
                    content: data,
                    btn:['确定','取消'],
                    yes:function () {
                        //角色名称不能为空
                        var rolename = $('input[name=rolename]').val();
                        if(isEmpty(rolename)){
                            layer.msg("角色名称不能为空",{icon: 5});
                            return false;
                        }
                        if(isEmpty(roleid)){
                            layer.msg("角色ID不能为空",{icon: 5});
                            return false;
                        }
                        $.ajax({
                            url:"/system/RoleManageCtrl/editRole?roleid="+roleid,
                            datatype:"json",
                            type:"post",
                            data:$("#editRoleForm").serialize(),
                            success:function (data) {
                                if(data.error != null && data.error != ""){
                                    layer.msg("修改失败!",{icon: 5});
                                }else{
                                    window.top.layer.msg("修改成功",{icon: 1});
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
            var roleid = $("#delRole").attr("value");
            if(isEmpty(roleid)){
                layer.msg("权限id不能为空",{icon: 5});
                return false;
            }
            window.location.href = "/system/RoleManageCtrl/delRole?roleid="+roleid;
        });
    })
});