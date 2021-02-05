package com.linyy.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 通用crud
 *
 * @param <T>
 */
public interface CrudService<T, E> {

    /**
     * 保存
     *
     * @param bean
     * @return
     */
    int save(T bean);

    /**
     * 更新
     *
     * @param bean
     * @return
     */
    int update(T bean);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(E id);

    /**
     * 批量删除
     *
     * @param beans
     * @return
     */
    int delete(List<E> ids);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    IPage<T> findPage(Map<String, Object> params);
}
