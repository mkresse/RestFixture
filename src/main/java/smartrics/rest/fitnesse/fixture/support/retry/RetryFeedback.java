package smartrics.rest.fitnesse.fixture.support.retry;

import smartrics.rest.fitnesse.fixture.support.CellFormatter;
import smartrics.rest.fitnesse.fixture.support.CellWrapper;

public class RetryFeedback {
    private final CellWrapper statusPatternCell;
    private final CellWrapper bodyPatternCell;
    private final CellFormatter formatter;
    private final String initialStatusValue;
    private final String initialBodyValue;


    public RetryFeedback(CellWrapper statusPatternCell, CellWrapper bodyPatternCell, CellFormatter formatter) {
        this.statusPatternCell = statusPatternCell;
        this.bodyPatternCell = bodyPatternCell;
        this.formatter = formatter;

        this.initialStatusValue = statusPatternCell.body();
        this.initialBodyValue = bodyPatternCell.body();
    }

    public void statusMatch(boolean success) {
        if (success) {
            statusPatternCell.body(initialStatusValue);
        } else {
            statusPatternCell.body(formatter.gray("Status pattern did not match"));
        }
    }

    public void bodyMatch(boolean success) {
        if (success) {
            bodyPatternCell.body(initialBodyValue);
        } else {
            bodyPatternCell.body(formatter.gray("Body pattern did not match"));
        }
    }

    public CellWrapper getStatusPatternCell() {
        return statusPatternCell;
    }

    public CellWrapper getBodyPatternCell() {
        return bodyPatternCell;
    }

    public CellFormatter getFormatter() {
        return formatter;
    }
}
