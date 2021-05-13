package edu.axboot.domain.standard;


import com.chequer.axboot.core.annotations.Comment;
import edu.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "PMS_ROOM")
public class RoomInfo extends SimpleJpaModel<Long> {

	@Id
	@Column(name = "ID", precision = 19, nullable = false)
	@Comment(value = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ROOM_NUM", length = 10, nullable = false)
	@Comment(value = "객실 번호")
	private String roomNum;

	@Column(name = "ROOM_TYP_CD", length = 20, nullable = false)
	@Comment(value = "객실 타입 CD")
	private String roomTypCd;

	@Column(name = "DND_YN", length = 1, nullable = false)
	@Comment(value = "DND 여부")
	private String dndYn;

	@Column(name = "EB_YN", length = 1, nullable = false)
	@Comment(value = "EB 여부")
	private String ebYn;

	@Column(name = "ROOM_STTUS_CD", length = 20)
	@Comment(value = "객실 상태 CD")
	private String roomSttusCd;

	@Column(name = "CLN_STTUS_CD", length = 20)
	@Comment(value = "청소 상태 CD")
	private String clnSttusCd;

	@Column(name = "SVC_STTUS_CD", length = 20)
	@Comment(value = "서비스 상태 CD")
	private String svcSttusCd;

	@Builder
	public RoomInfo(String roomNum,String roomTypCd,String dndYn,String ebYn,
					String roomSttusCd,String clnSttusCd,String svcSttusCd){
		this.roomNum =roomNum;
		this.roomTypCd = roomTypCd;
		this.dndYn = dndYn;
		this.ebYn =ebYn;
		this.roomSttusCd = roomSttusCd;
		this.clnSttusCd =clnSttusCd;
		this.svcSttusCd = svcSttusCd;
	}


	public void update(String roomTypCd,String dndYn,String ebYn,
					   String roomSttusCd,String clnSttusCd,String svcSttusCd) {

		this.roomTypCd = roomTypCd;
		this.dndYn = dndYn;
		this.ebYn =ebYn;
		this.roomSttusCd = roomSttusCd;
		this.clnSttusCd =clnSttusCd;
		this.svcSttusCd = svcSttusCd;
	}

/*
@Override
public RoomInfoId getId() {
return RoomInfoId.of();
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class RoomInfoId implements Serializable {

}*/
}