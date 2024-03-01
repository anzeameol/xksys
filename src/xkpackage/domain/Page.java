package xkpackage.domain;

import java.util.List;

public class Page<E> {
    public List<E> list;
    public List<E> page;
    public int curPage;
    public int fromIndex;
    public int toIndex;
    public int totRecord;
    public int totPage;
    public int pageSize;
    public E limit;
    public void init(List<E> list,int pageSize)
    {
        this.totRecord = list.size();
        this.totPage = (totRecord - 1) / pageSize + 1;
        this.curPage = 0;
        this.pageSize = pageSize;
        this.fromIndex = 0;
        this.toIndex = Math.min((curPage + 1)*pageSize,totRecord);
        this.list = list;
        this.page = list.subList(fromIndex,toIndex);
    }
    public void setList(List<E> list)
    {
        this.totRecord = list.size();
        this.totPage = (totRecord - 1) / pageSize + 1;
        this.fromIndex = curPage*pageSize;
        this.toIndex = Math.min((curPage + 1)*pageSize,totRecord);
        this.list = list;
        this.page = list.subList(fromIndex,toIndex);
    }
    public List<E> getCurPage()
    {
        return page;
    }
    public void setNextPage()
    {
        curPage = Math.min(curPage + 1, totPage - 1);
        fromIndex = curPage*pageSize;
        toIndex = Math.min((curPage + 1)*pageSize,totRecord);
        page = list.subList(fromIndex,toIndex);
    }
    public void setLastPage()
    {
        curPage = Math.max(curPage - 1, 0);
        fromIndex = curPage*pageSize;
        toIndex = Math.min((curPage + 1)*pageSize,totRecord);
        page = list.subList(fromIndex,toIndex);
    }
    public void setFirstPage()
    {
        curPage = 0;
        fromIndex = 0;
        toIndex = Math.min(pageSize,totRecord);
        page = list.subList(fromIndex,toIndex);
    }
}
