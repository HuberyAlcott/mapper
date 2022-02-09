package dandelion.mapper.defines;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/** @author Marcus */
@Data
@EqualsAndHashCode
public class GeneralColumns implements Serializable {

  @TableField(value = "enable_status", fill = FieldFill.INSERT_UPDATE)
  private Integer enableStatus;

  @TableLogic
  @TableField(value = "delete_status", fill = FieldFill.INSERT_UPDATE)
  private Integer deleteStatus;

  @TableField(value = "create_timing", fill = FieldFill.INSERT)
  private Timestamp createTiming;

  @TableField(value = "update_timing", fill = FieldFill.INSERT_UPDATE)
  private Timestamp updateTiming;
}
