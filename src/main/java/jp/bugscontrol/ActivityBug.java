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

package jp.bugscontrol;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jp.bugscontrol.general.Bug;
import jp.util.Util;

public class ActivityBug extends ListActivity {
    private Bug bug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_bug);

        int server = getIntent().getIntExtra("server_position", -1);
        int bug_id = getIntent().getIntExtra("bug_id", -1);
        bug = ActivityRegister.servers.get(server).getBugFromId(bug_id);

        getListView().addHeaderView(getUserProfileView());
        getListView().setAdapter(new AdapterComment(this, bug.getComments()));
    }

    public View getUserProfileView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bug_info, getListView(), false);

        ImageLoader.loadImage("http://www.gravatar.com/avatar/" + Util.md5(bug.getReporter()), (ImageView) view.findViewById(R.id.reporter_img));
        ImageLoader.loadImage("http://www.gravatar.com/avatar/" + Util.md5(bug.getAssignee()), (ImageView) view.findViewById(R.id.assignee_img));

        ((TextView) view.findViewById(R.id.summary)).setText(bug.getSummary());
        ((TextView) view.findViewById(R.id.priority)).setText(bug.getPriority());
        ((TextView) view.findViewById(R.id.status)).setText(bug.getStatus());

        ((TextView) view.findViewById(R.id.description)).setText(bug.getDescription());

        return view;
    }
}