package delegated_property

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * Desc: Implementing delegated property
 * Created by Chiclaim on 2018/9/30.
 */


open class PropertyChangeAware {
    private val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class People(val name: String, age: Int, salary: Float) : PropertyChangeAware() {

    //todo

}