package modelo

/**
 * Wrapper class for game information retrieved from the external API (ApiShark).
 * Acts as an adapter between the API response format and the application's domain model.
 *
 * @property info Raw game information data structure from the API
 */
class InfoJogo(val info: InfoApiShark) {
    /**
     * Provides a string representation of the game information.
     * Delegates to the toString() implementation of the InfoApiShark object.
     *
     * @return String representation of the API game information
     */
    override fun toString(): String {
        return info.toString()
    }
}