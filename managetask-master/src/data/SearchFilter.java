/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javax.microedition.rms.RecordFilter;

/**
 *
 * @author I320499
 */
class SearchFilter implements RecordFilter {

    private String searchText = null;

    public SearchFilter(String searchText) {
        // Text to find
        this.searchText = searchText.toLowerCase();
    }

    public boolean matches(byte[] candidate) {
        String str = new String(candidate).toLowerCase();
// Does the text exist?
        if (searchText != null && str.indexOf(searchText) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
