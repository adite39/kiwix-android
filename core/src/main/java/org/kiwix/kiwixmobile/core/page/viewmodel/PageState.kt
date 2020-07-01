/*
 * Kiwix Android
 * Copyright (c) 2020 Kiwix <android.kiwix.org>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.kiwix.kiwixmobile.core.page.viewmodel

import org.kiwix.kiwixmobile.core.page.adapter.Page
import org.kiwix.kiwixmobile.core.page.adapter.PageRelated

abstract class PageState {
  abstract val pageItems: List<Page>
  val isInSelectionState: Boolean by lazy { pageItems.any(Page::isSelected) }
  abstract val numberOfSelectedItems: Int
  abstract val filteredPageItems: List<PageRelated>
  abstract val showAll: Boolean
  abstract val currentZimId: String?
  abstract val searchTerm: String
  fun toggleSelectionOfItem(page: Page): PageState {
    val newList = pageItems.map {
      if (it.id == page.id) it.apply {
        isSelected = !isSelected
      } else it
    }
    return copyWithNewItems(newList)
  }

  abstract fun copyWithNewItems(newItems: List<Page>): PageState
}
