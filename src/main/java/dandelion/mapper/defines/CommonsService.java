package dandelion.mapper.defines;

import com.baomidou.mybatisplus.core.metadata.IPage;
import dandelion.wrapper.builder.ResultBuilder;
import dandelion.wrapper.defines.Paged;
import dandelion.wrapper.returns.GeneralService;
import dandelion.wrapper.returns.PagingResult;

import java.util.Collection;

/** @author Marcus */
public interface CommonsService extends GeneralService {

  /**
   * 返回分页对象
   *
   * @param source JPA分页对象
   * @param <E> 数据类型
   * @return 通用分页对象
   */
  default <E> PagingResult<E> paging(IPage<E> source) {
    final var paged = buildPaging(source, source.getRecords());
    return ResultBuilder.temporary(paged);
  }

  /**
   * 返回分页对象
   *
   * @param source JPA分页对象
   * @param record 实际数据集合
   * @param <T> 实际返回类型
   * @param <E> 实体数据类型
   * @return 通用分页对象
   */
  default <E, T> PagingResult<T> paging(IPage<E> source, Collection<T> record) {
    final var paged = buildPaging(source, record);
    return ResultBuilder.temporary(paged);
  }

  /**
   * 构造公用分页对象
   *
   * @param source JPA分页对象
   * @param record 实际数据集合
   * @param <T> 实际返回类型
   * @param <E> 实体数据类型
   * @return 通用分页对象
   */
  private <E, T> Paged<T> buildPaging(final IPage<E> source, final Collection<T> record) {
    final var paged = new Paged<T>();
    final long page = source.getPages();
    final long size = source.getSize();
    final long sums = source.getTotal();
    final var offset = offset(page, size);
    paged.setPage(page);
    paged.setSize(size);
    paged.setSkip(offset);
    paged.setSums(sums);
    paged.setList(record);
    return paged;
  }

  /**
   * 计算偏移量
   *
   * @param page 当前页
   * @param size 页大小
   * @return 偏移量
   */
  private long offset(long page, long size) {
    final long x = page < 0 ? 0 : page;
    final long y = size < 0 ? 20 : size;
    return x * y;
  }
}
