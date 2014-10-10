/*
 *  BugsControl
 *  Copyright (C) 2013  Jon Ander Peñalba
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.bugscontrol.general;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import jp.bugscontrol.AdapterBug;

public abstract class Product {
    protected final Server server;

    protected int id;
    protected String name;
    protected String description;

    protected final List<Bug> bugs = new ArrayList<Bug>();

    protected AdapterBug adapter;
    protected Activity activity;

    public Product(Server server) {
        this.server = server;
    }

    protected abstract void loadBugs();

    public void setAdapterBug(final AdapterBug adapter, final Activity activity) {
        this.adapter = adapter;
        this.activity = activity;

        activity.setProgressBarIndeterminateVisibility(true);
        loadBugs();
    }

    protected void bugsListUpdated() {
        adapter.notifyDataSetChanged();
        activity.setProgressBarIndeterminateVisibility(false);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Server getServer() {
        return server;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void addBug(final Bug bug) {
        bugs.add(bug);
    }

    public void addBugs(final List<Bug> newBugs) {
        bugs.addAll(newBugs);
    }

    public void clearBugs() {
        bugs.clear();
    }
}
