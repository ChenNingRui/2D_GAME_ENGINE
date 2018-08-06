package basic.event;

import java.util.EventListener;

public interface ECSEventListener extends EventListener{
	void systemEvent(ECSEvent e);
}
