package com.sqli.workshop.business;

import com.sqli.workshop.bo.GhibliItem;
import com.sqli.workshop.viewmodel.GhibliViewModel;

import java.util.ArrayList;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliBusinessService {

    public ArrayList<GhibliViewModel> generateViewModels(ArrayList<GhibliItem> data) {
        final ArrayList<GhibliViewModel> result = new ArrayList<>();

        for (GhibliItem item : data) {
            GhibliViewModel model = new GhibliViewModel(item);
            result.add(model);
        }
        return result;
    }
}
