    <div id="dlg-person" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons-person">
        <div class="ftitle">Person Information</div>
        <form id="fm-person" method="post" novalidate class="fm">
            <div class="fitem">
                <label>First Name:</label>
                <input name="first_name" class="easyui-validatebox" required="true">
            </div>
            <div class="fitem">
                <label>Last Name:</label>
                <input name="last_name" class="easyui-validatebox" required="true">
            </div>
          
        </form>
    </div>
    <div id="dlg-buttons-person">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePerson()"><spring:message code='label.save'/></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg-person').dialog('close')"><spring:message code='label.cancel'/></a>
    </div>
    </div>
    <script type="text/javascript">
        var url;
        function newPerson(){
            $('#dlg-person').dialog('open').dialog('setTitle',"<spring:message code='label.new'/> Person");
            $('#fm-person').form('clear');
            url = './people/save';
        }
        function editPerson(){
            var row = $('#dg-person').datagrid('getSelected');
            if (row){
                $('#dlg-person').dialog('open').dialog('setTitle',"<spring:message code='label.edit'/> Person");
                $('#fm-person').form('load',row);
                url = './people/update/'+row.id;
            }
        }
        function savePerson(){
            $('#fm-person').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
            
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg-person').dialog('close');        // close the dialog
                        $('#dg-person').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyPerson(){
            var row = $('#dg-person').datagrid('getSelected');
            if (row){
                $.messager.confirm("<spring:message code='label.confirm'/>","<spring:message code='label.message.destroy' arguments='Person'/>",function(r){
                    if (r){
                        $.post('./people/destroy/'+row.id,function(result){
                            if (result.success){
                                $('#dg-person').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
  


