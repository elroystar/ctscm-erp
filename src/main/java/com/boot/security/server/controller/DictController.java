package com.boot.security.server.controller;

import com.boot.security.server.dao.DictDao;
import com.boot.security.server.model.Dict;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dicts")
public class DictController {

    @Autowired
    private DictDao dictDao;

    @PreAuthorize("hasAuthority('dict:add')")
    @PostMapping
    @ApiOperation(value = "保存")
    public Dict save(@RequestBody Dict dict) {
        Dict d = dictDao.getByTypeAndK(dict.getType(), dict.getK());
        if (d != null) {
            throw new IllegalArgumentException("类型和key已存在");
        }
        dictDao.save(dict);

        return dict;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Dict get(@PathVariable Long id) {
        return dictDao.getById(id);
    }

    @PreAuthorize("hasAuthority('dict:add')")
    @PutMapping
    @ApiOperation(value = "修改")
    public Dict update(@RequestBody Dict dict) {
        dictDao.update(dict);

        return dict;
    }

    @PreAuthorize("hasAuthority('dict:query')")
    @GetMapping(params = {"start", "length"})
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return dictDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Dict> list(PageTableRequest request) {
                return dictDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("getAllDict")
    @ApiOperation(value = "获取全部字典")
    public List<Dict> getAllDict(PageTableRequest request) {
        return dictDao.getAllDict(request.getParams());
    }

    @PreAuthorize("hasAuthority('dict:del')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        dictDao.delete(id);
    }

    @GetMapping(params = "type")
    public List<Dict> listByType(String type) {
        return dictDao.listByType(type);
    }

    @GetMapping("/listByName")
    public List<Dict> listByName(String name) {
        return dictDao.listByName(name);
    }
}
