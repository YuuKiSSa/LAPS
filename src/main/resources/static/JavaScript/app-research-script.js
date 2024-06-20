function enableEdit(button) {
    var row = button.parentNode.parentNode; // 获取按钮的父级行
    var displayInput = row.querySelector('input[name="applicationTypeDisplay"]'); // 获取应用类型的显示 input 元素
    var hiddenInputId = row.querySelector('input[name="applicationType.id"]'); // 获取隐藏的应用类型 ID input 元素
    var hiddenInputType = row.querySelector('input[name="applicationType.type"]'); // 获取隐藏的应用类型 Type input 元素

    if (displayInput) {
        var select = document.createElement('select'); // 创建一个新的 select 元素
        select.name = 'applicationType.id'; // 设置表单名称匹配后端模型

        var types = [
            { id: 1, type: 'Medical' },
            { id: 2, type: 'Compensation' },
            { id: 3, type: 'Annual' }
        ]; // 假设这些是应用类型

        types.forEach(function(type) {
            var option = document.createElement('option'); // 创建一个新的选项
            option.value = type.id;
            option.text = type.type;
            if (type.type === hiddenInputType.value) { // 如果是当前应用类型
                option.selected = true;
            }
            select.appendChild(option); // 添加选项到 select 元素中
        });

        select.addEventListener('change', function() {
            hiddenInputId.value = select.value; // 当选择改变时，更新隐藏的 input 元素值
            hiddenInputType.value = select.options[select.selectedIndex].text; // 更新隐藏的类型文本值
        });

        displayInput.parentNode.replaceChild(select, displayInput); // 替换显示的 input 元素为 select 元素
    }
}