package edu.axboot.domain.reservation;

import com.chequer.axboot.core.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import edu.axboot.domain.BaseJpaModel;
import edu.axboot.domain.SimpleJpaModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PMS_CHK_MEMO")
public class ChkMemo extends BaseJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Comment(value = "ID")
	private Long id;

	@Column(name = "RSV_NUM", length = 20)
	@Comment(value = "예약번호")
	private String rsvNum;

	@Column(name = "SNO", precision = 10, nullable = false)
	@Comment(value = "일련번호")
	private Integer sno;

	@Column(name = "MEMO_CN", length = 4000, nullable = false)
	@Comment(value = "메모 내용")
	private String memoCn;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	@Column(name = "MEMO_DTTI", nullable = false)
	@Comment(value = "메모 일시")
	private Timestamp memoDtti;

	@Column(name = "MEMO_MAN", length = 100, nullable = false)
	@Comment(value = "메모 자")
	private String memoMan;

	@Column(name = "DEL_YN", length = 1, nullable = false)
	@Comment(value = "삭제 여부")
	private String delYn;


    @Override
    public Long getId() {
        return id;
    }


	@Builder
	public ChkMemo(Long id, String rsvNum, int sno, String memoCn, Timestamp memoDtti, String memoMan, String delYn, boolean isCreated, boolean isModified, boolean isDeleted) {
		this.id = id;
		this.rsvNum = rsvNum;
		this.sno = sno;
		this.memoCn = memoCn;
		this.memoDtti = memoDtti;
		this.memoMan = memoMan;
		this.delYn = delYn;
		this.__created__ = isCreated;
		this.__modified__ = isModified;
		this.__deleted__ = isDeleted;

	}
//
//	public void update(Timestamp memoDtti, String memoCn) {
//		this.memoDtti = memoDtti;
//		this.memoCn = memoCn;
//
//	}

	public void update(String memoCn) {
		this.memoCn = memoCn;
	}

	public void delete() {
		this.delYn = "Y";
	}
}