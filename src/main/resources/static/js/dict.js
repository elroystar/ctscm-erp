function showDictSelect(id, type, all) {
    var data = getDict(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>全部</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function showDictNameSelect(id, name, all) {
    var data = getAllDictByName(name);
    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>全部</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function showDictSelectByName(id, name, all) {
    var data = getDictByName(name);
    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>全部</option>");
    }

    $.each(data, function (k, v) {
        select.append("<option value ='" + k + "'>" + v + "</option>");
    });

    return data;
}

function getDict(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type: 'get',
            url: '/dicts?type=' + type,
            async: false,
            success: function (data) {
                v = {};
                $.each(data, function (i, d) {
                    v[d.k] = d.val;
                });

                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getDictByName(name) {
    v = {};
    $.ajax({
        type: 'get',
        url: '/dicts/listByName?name=' + name,
        async: false,
        success: function (data) {
            v = {};
            $.each(data, function (i, d) {
                v[d.k] = d.val;
            });
        }
    });
    return JSON.parse(JSON.stringify(v));
}

function getAllDictByName(name) {
    v = {};
    $.ajax({
        type: 'get',
        url: '/dicts/getAllDict?name=' + name,
        async: false,
        success: function (data) {
            $.each(data, function (i, d) {
                v[d.type] = d.type;
            });
        }
    });
    return JSON.parse(JSON.stringify(v));
}
