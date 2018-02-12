package manddev.com.testavito.business;

import java.util.ArrayList;

import manddev.com.testavito.bo.GhibliItem;
import manddev.com.testavito.viewmodel.GhibliViewModel;

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
