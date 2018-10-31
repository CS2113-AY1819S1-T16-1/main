package seedu.address.ui;

/**
 * This class handles utility methods for the rendering of Strings to HTML cards.
 */
public class HtmlCardProcessor {

    public static final String CARD_NO_TITLE = "(No Title)";

    /**
     * The top-most tag required to render a card in HTML.
     */
    public static String getCardStart() {
        return "<div class=\"card text-white bg-primary mb-3\">";
    }

    /**
     * Constructs a card header section in the card.
     *
     * Uses the header style {@code headingType} specified.
     *
     * @param headingType
     * @param header
     * @return string containing the HTML card header tag
     */
    public static String renderCardHeader(String headingType, String header) {
        StringBuilder sb = new StringBuilder();

        sb.append("  <" + headingType + " class=\"card-header\">");
        sb.append(header);
        sb.append("</" + headingType + ">\n");

        return sb.toString();
    }

    /**
     * Constructs a card title section in the card.
     *
     * @param title
     * @return string containing the HTML card title tag
     */
    public static String renderCardTitle(String title) {
        StringBuilder sb = new StringBuilder();

        sb.append("    <h4 class=\"card-title\">");
        sb.append(title);
        sb.append("</h4>");

        return sb.toString();
    }

    /**
     * Constructs a card subtitle section in the card.
     *
     * @param subtitle
     * @return string containing the HTML card subtitle tag
     */
    public static String renderCardSubtitle(String subtitle) {
        StringBuilder sb = new StringBuilder();

        sb.append("    <h6 class=\"card-subtitle mb-2 text-muted\">");
        sb.append(subtitle);
        sb.append("</h6>");

        return sb.toString();
    }

    /**
     * Constructs a card text section in the card.
     *
     * @param text
     * @return string containing the HTML card text tag
     */
    public static String renderCardText(String text) {
        StringBuilder sb = new StringBuilder();

        sb.append("    <p class=\"card-text\">");
        sb.append(text);
        sb.append("</p>\n");

        return sb.toString();
    }

    /**
     * This method renders a footer to the card.
     */
    public static String renderCardFooter(String footer) {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"card-footer text-muted\">\n");
        sb.append("  " + footer + "\n");
        sb.append(getDivEndTag());

        return sb.toString();
    }

    public static String getCardBodyStart() {
        return "<div class=\"card-body\">\n";
    }

    public static String getDivEndTag() {
        return "</div>\n";
    }

    /**
     * This method converts HTML special characters in the string to
     * its literal character representation.
     *
     * The character '&' is replaced first so that HTML character entities in the string
     * will be escaped.
     *
     * It is followed by replacing '<', and '>', to prevent
     * user input to be identified as HTML tags.
     *
     * String escape characters are converted to their HTML counterparts.
     *
     * @param text
     * @return an HTML adapted string
     */
    public static String adaptToHtml(String text) {
        return text
                .replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll(" ", "&nbsp;")
                .replaceAll("\t", "&emsp;&emsp;")
                .replaceAll("(\r\n|\n)", "<br />");
    }
}
