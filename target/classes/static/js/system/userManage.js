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

    /**跳转到修改用户界面*/
    $("#editUser").on('click',function () {
           var userid = $(this).attr("value");
           if(isEmpty(userid)){
                layer.msg("userid不能为空!修改失败",{icon: 5});
                return false;
           }
           $.ajax({
               url:"/system/userManage/toEditUser",
               type: "POST"
            }).done(function(data) {
                layer.open({
                    type: 1,
                    shade: [0.6, '#AAAAAA'],
                    area: ["450px", "500px"],
                    title: "修改用户密码",
                    content: data,
                    btn:['确定','取消'],
                    yes:function () {
                        $.ajax({
                            url:"/system/userManage/editUser?userid="+userid,
                            datatype:"json",
                            type:"post",
                            data:$("#editUserForm").serialize(),
                            success:function (data) {
                                if(data.error != null && data.error != ""){
                                    layer.msg(data.error,{icon: 5});
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

});

/**删除用户*/
function delUser(obj){
    var userid = $(obj).attr("value");
    layer.confirm('是否删除?', function(index){
        if(isEmpty(userid)){
           layer.msg("userid不能为空!删除失败",{icon: 5});
           return false;
        }
        window.location.href="/system/userManage/delUser?userid="+userid;
     });

}

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


        //分配角色权限
var userManageVar = {};
function chmodPri (obj){
            if (isEmpty($(obj).attr('value'))) {
                layer.msg("用户id不能为空！！", {icon: 5});
                return false;
            }
            var userid = $(obj).attr('value');
            $.ajax({
                url: "/system/userManage/toChmodPage",
                type: "GET"
            }).done(function (data) {
                layer.open({
                    id:"chomdroleandper",
                    type: 1,
                    shade: [0.6, '#AAAAAA'],
                    area: ["450px", "500px"],
                    title: "分配用户角色",
                    content: data,
                    btn: ['确定', '取消'],
                    yes: function () {
                        //获取下拉框勾选项
                        userManageVar.selectedArr = new Array();
                        $("#chmodUserPriForm .am-selected-list .am-checked").each(function (i) {
                            userManageVar.selectedArr[i] = $(this).attr("data-value");
                        });
                        $.ajax({
                            url: "/system/userManage/saveRoleForUser",
                            datatype: "json",
                            type: "POST",
                            data: {
                                "userid":userid,
                                "roleArr":userManageVar.selectedArr+""
                            },
                            success: function (data) {
                                if (data.error != null && data.error != "") {
                                    layer.msg(data.error, {icon: 5});
                                } else {
                                    window.top.layer.msg("保存成功", {icon: 1});
                                    window.location.reload();
                                }
                            }
                        })
                    },
                    btn2: function () {

                    }
                });
            });
    }

function initMenuTreeForUserPage(){
    $("#treeDemoForUserPage").innerHTML = "";
    var zTreeObj;
    var setting = {
        view: {
            selectedMulti: true
        },
        async:{
            enable:true,
            url:"/system/MenuManageCtrl/loadSonMenu",
            autoParam:["id","name"]
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkDisabledInherit: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: false,
            editNameSelectAll: true,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        callback: {
        }
    };
    var zNodes ;
    $.ajax({
        url: "/system/MenuManageCtrl/returnZtreeData",
        type: "post",
        datatype:"json",
        success: function (data) {
            if(data == null || data == ''){
                layer.msg(data.error);
                return false;
            }else{
                zNodes = data.data;
                $.fn.zTree.init($("#treeDemoForUserPage"), setting, eval('(' + zNodes + ')')); //zNodes需要 转换成json对象
            }
        }
    })
}




