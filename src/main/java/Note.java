/**
 * @(#)Note.java, Apr 08, 2020.
 * <p>
 * Copyright 2020 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import lombok.Builder;
import lombok.Data;

/**
 * @author wangsy
 */

@Data
@Builder
public class Note {
    @Builder.Default
    protected String bookTitle = "";

    @Builder.Default
    protected String timestamp = "";

    @Builder.Default
    protected String highlight = "";

    @Builder.Default
    protected String location = "";

    @Builder.Default
    protected String page = "";

    @Builder.Default
    protected String info = "";

}