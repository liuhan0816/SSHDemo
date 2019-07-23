package com.arthur.web.fpl;

import com.arthur.web.core.common.dao.DAOFactory;
import com.arthur.web.core.common.dao.JdbcDAO;

import java.util.*;


public class PermitUtil {

	// public Set getUserPermit(String parentFunctionId, List list) {
	//
	// TreeSet treeSet = generateRootTree(list);
	//
	// return getFunTree(treeSet, parentFunctionId);
	// }

	public Set getUserPermit(String parentFunctionId, String userId) {
		TreeSet treeSet = generateRootTree(userId);
		return getFunTree(treeSet, parentFunctionId);
	}

	private List getPermit(String userId) {
		JdbcDAO dao = DAOFactory.getJdbcDAO("scmJdbcDAO");
		String sql = "select * from v_sys_func_perm_inst t where t.OWNERID='"
				+ userId + "' order by t.FUNCTIONORDER";
		List list = dao.getJdbcTemplate().queryForList(sql);
		List menuList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);
			String functionid = map.get("functionid") == null ? null : map.get(
					"functionid").toString();
			String parentid = map.get("parentid") == null ? null : map.get(
					"parentid").toString();
			String functionname = map.get("functionname") == null ? null : map
					.get("functionname").toString();
			String functionurl = map.get("functionurl") == null ? null : map
					.get("functionurl").toString();
			Long functiontype = map.get("functiontype") == null ? null : Long
					.valueOf(map.get("functiontype").toString());
			Long openmode = map.get("OPENMODE") == null ? null : Long
					.valueOf(map.get("OPENMODE").toString());
			Long functionOrder = map.get("FUNCTIONORDER") == null ? null : Long
					.valueOf(map.get("FUNCTIONORDER").toString());

			T4plFuncDef def = new T4plFuncDef();
			def.setFunctionid(functionid);
			def.setParentid(parentid);
			def.setFunctionname(functionname);
			def.setFunctionurl(functionurl);
			def.setFunctiontype(functiontype);
			def.setOpenmode(openmode);
			def.setFunctionorder(functionOrder);
			menuList.add(def);
		}
		return menuList;

	}

	protected TreeSet generateRootTree(String userId) {
		List list = getPermit(userId);
		TreeSet treeSet = new TreeSet();

		T4plFuncDef funcDef = null;
		for (int i = 0; i < list.size(); i++) {
			T4plFuncDef t4plFuncDef = (T4plFuncDef) list.get(i);

			if (t4plFuncDef.getParentid() == null) {
				funcDef = t4plFuncDef;
				generateTree(list, funcDef);
				treeSet.add(funcDef);
			}
		}
		return treeSet;
	}

	protected Set getFunTree(Set treeSet, String parentFunctionId) {
		if (parentFunctionId == null) {
			return treeSet;
		} else {
			TreeSet functionSet = new TreeSet();
			Iterator iterator = treeSet.iterator();
			while (iterator.hasNext()) {
				T4plFuncDef t4plFuncDef = (T4plFuncDef) iterator.next();
				if (t4plFuncDef.getParentid() != null
						&& t4plFuncDef.getParentid().equals(parentFunctionId)) {

					return treeSet;

				} else {
					Set childSet = getFunTree(t4plFuncDef.getFunctionList(),
							parentFunctionId);
					if (childSet.size() > 0) {
						return childSet;
					}
				}
			}
			return functionSet;
		}

	}

	private void generateTree(List list, T4plFuncDef t4plFuncDef) {
		for (int i = 0; i < list.size(); i++) {
			T4plFuncDef funcdef = (T4plFuncDef) list.get(i);
			if (funcdef.getParentid() != null
					&& funcdef.getParentid()
							.equals(t4plFuncDef.getFunctionid())) {

				t4plFuncDef.getFunctionList().add(funcdef);
				if (funcdef.getFunctiontype().intValue() == 0) {
					generateTree(list, funcdef);
				}
			}
		}

	}

}
