@XmlJavaTypeAdapters(value = { @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class, type = java.time.LocalDateTime.class),
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class, type = java.time.LocalDate.class) })
package <%= basePackage %>.model.dto.<%= snakeResourceName %>;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

//TODO
//LocalDateAdapter
//LocalDateTimeAdapter
