package com.arthur.web.pojo.business;

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
 * @Date 2019-07-03 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="user" )
public class User  implements Serializable {

	private static final long serialVersionUID =  6483529327679536201L;

	/**
	 * 用户名
	 */
    @Id
	@GeneratedValue(generator = "fendo_Generator")
	@GenericGenerator(name = "fendo_Generator", strategy = "assigned")
   	@Column(name = "userId" )
	private String userId;

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
	 * 创建时间
	 */
   	@Column(name = "createdTime" )
	private Date createdTime;

	/**
	 * 操作时间
	 */
   	@Column(name = "opdateTime" )
	private Date opdateTime;

	/**
	 * 性别-1未知0女1男
	 */
   	@Column(name = "gender" )
	private String gender;

	/**
	 * 身份证
	 */
   	@Column(name = "certificateNumber" )
	private String certificateNumber;

	/**
	 * 手机
	 */
   	@Column(name = "mobile" )
	private String mobile;

	/**
	 * 电话
	 */
   	@Column(name = "telephoneNumber" )
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
