/* 
Copyright 2019 Matthias Krane

This file is part of the Warehouse Management System mywms

mywms is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.
 
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
*/
package de.wms2.mywms.inventory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.wms2.mywms.entity.PersistenceManager;
import de.wms2.mywms.exception.BusinessException;

/**
 * @author krane
 * 
 */
@Stateless
public class UnitLoadTypeEntityService {
	@Inject
	private PersistenceManager manager;

	public UnitLoadType create(String name) throws BusinessException {
		UnitLoadType type = manager.createInstance(UnitLoadType.class);
		type.setName(name);

		manager.persistValidated(type);

		return type;
	}

	public UnitLoadType read(String name) {
		String jpql = "SELECT entity FROM " + UnitLoadType.class.getName() + " entity ";
		jpql += " where entity.name=:name";
		Query query = manager.createQuery(jpql);
		query.setParameter("name", name);
		try {
			UnitLoadType item = (UnitLoadType) query.getSingleResult();
			return item;
		} catch (NoResultException e) {
		}
		return null;
	}
}