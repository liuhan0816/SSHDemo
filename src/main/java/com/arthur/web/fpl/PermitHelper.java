package com.arthur.web.fpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PermitHelper {
	public final static String hascm_id = "ff808081404d8b6601407a5d6cf70006";

	public static List getUserPermit(String parentFunctionId, String userId) {

		PermitUtil permitUtil = new PermitUtil();
		Set set = permitUtil.getUserPermit(parentFunctionId, userId);
		List funlist = new ArrayList();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			T4plFuncDef funcDef = (T4plFuncDef) it.next();
			funlist.add(funcDef);
		}
		return funlist;
	}

	// 根据userid取得该账号的菜单
	public static List getUserPermit(String userId) {

		PermitUtil permitUtil = new PermitUtil();
		Set set = permitUtil.getUserPermit(hascm_id, userId);
		List funlist = new ArrayList();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			T4plFuncDef funcDef = (T4plFuncDef) it.next();
			funlist.add(funcDef);
		}
		return funlist;
	}

	// 根据不同的orgType取得parentFunctionId
	public static String getParentFunctionId(String orgType, String ou) {
		String parentFunctionId = PermitHelper.hascm_id;

		return parentFunctionId;
	}

}
