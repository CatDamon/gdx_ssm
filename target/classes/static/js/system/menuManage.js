$(function(){
    initMenuTree();
    /**
     * 保存菜单
     * */
    $(".saveMenu").on("click",function () {
        //校验非空
        var temp = validateNoNull($("#addMenuForm")[0]);
        if(!temp){
            return false;
        };
        //校验数字
        if(!/^[0-9]*$/.test($("input[name=orders]").val())){
            layer.tips('请输入数字',$("input[name=orders]"), {
                tips: 3
            });
            return false;
        }
        //二次弹框
        layer.confirm('确定要保存吗?', {icon: 3, title:'提示'}, function(index){
            $.ajax({
                url:"/system/MenuManageCtrl/saveMenu",
                datatype:"json",
                data:$("#addMenuForm").serialize(),
                type:"post",
                success:function (data) {
                    if(data.error!=null && data.error!=''){
                        layer.msg(data.error);
                    }else{
                        //添加成功
                        layer.msg("添加权限成功");
                        //此处重新生成菜单树


                    }

                }
            });

            layer.close(index); //请求成功关掉弹框
        });

    })


});

/**屏蔽显示菜单根目录*/
function isMenuOrPorint(value){
    //alert(value);
    //0是菜单,1是权限点
    if("0" === value){
        $(".com-isHeaderDiv").show("slow");
    }else{
        $(".com-isHeaderDiv").hide("slow");
    }
}
/**选择是否是根目录校验上级权限*/
function isHeader(value){
    //0是根目录,1是非根目录
    if("0"===value){
        //无需选择上级菜单

    }else{

    }
}



function initMenuTree(){
    var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        view: {
            selectedMulti: true
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "p", "N": "s" }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
        }
    };
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [
        {name:"test1", open:true, children:[
            {name:"test1_1"}, {name:"test1_2"}]},
        {name:"test2", open:true, children:[
            {name:"test2_1"}, {name:"test2_2"}]}
    ];
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

