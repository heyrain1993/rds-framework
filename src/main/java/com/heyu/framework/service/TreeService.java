package com.heyu.framework.service;

import com.heyu.framework.dao.TreeDao;
import com.heyu.framework.model.TreeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeService<T extends TreeEntity> extends BaseService{

    @Autowired
    private TreeDao<T> treeDao;

    public List<T> build(List<T> list){
        List<T> results = new ArrayList<>();//返回所有的树

        List<T> parents = new ArrayList<>();//list中最顶层节点

        //找出集合中所有最顶层节点
        for (T t1:list){

            boolean flag = true;//判断该元素是否是list中的最顶层
            for (T t2:list){
                if (t1.getParentId().equals(t2.getId()) ){
                    flag = false;
                }
            }
            if(flag){
                parents.add(t1);
            }
        }
        list.removeAll(parents);

        for (T parent:parents){

            results.add(buildTree(parent,list));
        }
        return results;
    }

    private T buildTree(T parent,List<T> list){
        for (T t:list){
            if(parent.getId().equals(t.getParentId())){
                if(parent.getChildren() == null){
                    parent.setChildren(new ArrayList<T>());
                }
                parent.getChildren().add(buildTree(t,list));
            }
        }
        return parent;
    }

    public void saveTree(T t){
        T parent = treeDao.findById(t.getParentId());
    }
}
