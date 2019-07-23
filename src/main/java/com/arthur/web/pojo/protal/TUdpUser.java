package com.arthur.web.pojo.protal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-07-17 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="t_udp_user" )
public class TUdpUser  implements Serializable {

	private static final long serialVersionUID =  4160305854084191436L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id" )
	private String id;

	/**
	 * 用户名
	 */
   	@Column(name = "user_code" )
	private String userCode;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 权限00无权限20管理员
	 */
   	@Column(name = "privilege" )
	private String privilege;

	/**
	 * 创作时间
	 */
   	@Column(name = "created_time" )
	private Date createdTime;

	/**
	 * 操作时间
	 */
   	@Column(name = "opdate_time" )
	private Date opdateTime;

	/**
	 * 性别-1未知0女1男
	 */
   	@Column(name = "gender" )
	private String gender;

	/**
	 * 身份证
	 */
   	@Column(name = "certificate_number" )
	private String certificateNumber;

	/**
	 * 手机
	 */
   	@Column(name = "mobile" )
	private String mobile;

	/**
	 * 电话
	 */
   	@Column(name = "telephone_number" )
	private String telephoneNumber;

	/**
	 * 邮箱
	 */
   	@Column(name = "mail" )
	private String mail;

	/**
	 * 档案
	 */
   	@Column(name = "biography" )
	private String biography;

	/**
	 * 姓名
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 昵称
	 */
   	@Column(name = "nickName" )
	private String nickName;

	/**
	 * 生日
	 */
   	@Column(name = "birthday" )
	private Date birthday;

}
