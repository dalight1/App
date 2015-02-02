package at.app.dalight.dalight;

/**
 * Created by Stephan on 23.01.2015.
 */
public class DaliCommands {

    public final static String OFF = "00";
    public final static String UP = "01";
    public final static String DOWN = "02";
    public final static String STEP_UP = "03";
    public final static String STEP_DOWN = "04";
    public final static String RECALL_MAX_LEVEL = "05";
    public final static String RECALL_MIN_LEVEL = "06";
    public final static String STEP_DOWN_AND_OFF = "07";
    public final static String ON_AND_STEP_UP = "08";
    public final static String ENABLE_DAPC_SEQUENCE = "09";
    public final static String GO_TO_LAST_ACTIVE_LEVEL = "0A";

    public final static String GO_TO_SCENE_0 = "10";
    public final static String GO_TO_SCENE_1 = "11";
    public final static String GO_TO_SCENE_2 = "12";
    public final static String GO_TO_SCENE_3 = "13";
    public final static String GO_TO_SCENE_4 = "14";
    public final static String GO_TO_SCENE_5 = "15";
    public final static String GO_TO_SCENE_6 = "16";
    public final static String GO_TO_SCENE_7 = "17";
    public final static String GO_TO_SCENE_8 = "18";
    public final static String GO_TO_SCENE_9 = "19";
    public final static String GO_TO_SCENE_10 = "1A";
    public final static String GO_TO_SCENE_11 = "1B";
    public final static String GO_TO_SCENE_12 = "1C";
    public final static String GO_TO_SCENE_13 = "1D";
    public final static String GO_TO_SCENE_14 = "1E";
    public final static String GO_TO_SCENE_15 = "1F";

    //Configuration Instructions
    public final static String RESET = "20";
    public final static String STORE_ACTUAL_LEVEL_IN_DTR0 = "21";
    public final static String SAVE_PERSISTENT_VARIABLES = "22";
    public final static String SET_OPERATING_MODE = "23";
    public final static String RESET_MEMORY_BANK = "24";
    public final static String IDENTIFY_DEVICE = "25";
    public final static String SET_MAX_LEVEL = "2A";
    public final static String SET_MIN_LEVEL = "2B";
    public final static String SET_SYSTEM_FAILURE_LEVEL = "2C";
    public final static String SET_POWER_ON_LEVEL = "2D";
    public final static String SET_FADE_TIME = "2E";
    public final static String SET_FADE_RATE = "2F";
    public final static String SET_EXTENDED_FADE_TIME = "30";

    public final static String SET_SCENE_0 = "40";
    public final static String SET_SCENE_1 = "41";
    public final static String SET_SCENE_2 = "42";
    public final static String SET_SCENE_3 = "43";
    public final static String SET_SCENE_4 = "44";
    public final static String SET_SCENE_5 = "45";
    public final static String SET_SCENE_6 = "46";
    public final static String SET_SCENE_7 = "47";
    public final static String SET_SCENE_8 = "48";
    public final static String SET_SCENE_9 = "49";
    public final static String SET_SCENE_10 = "4A";
    public final static String SET_SCENE_11 = "4B";
    public final static String SET_SCENE_12 = "4C";
    public final static String SET_SCENE_13 = "4D";
    public final static String SET_SCENE_14 = "4E";
    public final static String SET_SCENE_15 = "4F";

    public final static String REMOVE_FROM_SCENE_0 = "50";
    public final static String REMOVE_FROM_SCENE_1 = "51";
    public final static String REMOVE_FROM_SCENE_2 = "52";
    public final static String REMOVE_FROM_SCENE_3 = "53";
    public final static String REMOVE_FROM_SCENE_4 = "54";
    public final static String REMOVE_FROM_SCENE_5 = "55";
    public final static String REMOVE_FROM_SCENE_6 = "56";
    public final static String REMOVE_FROM_SCENE_7 = "57";
    public final static String REMOVE_FROM_SCENE_8 = "58";
    public final static String REMOVE_FROM_SCENE_9 = "59";
    public final static String REMOVE_FROM_SCENE_10 = "5A";
    public final static String REMOVE_FROM_SCENE_11 = "5B";
    public final static String REMOVE_FROM_SCENE_12 = "5C";
    public final static String REMOVE_FROM_SCENE_13 = "5D";
    public final static String REMOVE_FROM_SCENE_14 = "5E";
    public final static String REMOVE_FROM_SCENE_15 = "5F";

    public final static String ADD_TO_GROUP_0 = "60";
    public final static String ADD_TO_GROUP_1 = "61";
    public final static String ADD_TO_GROUP_2 = "62";
    public final static String ADD_TO_GROUP_3 = "63";
    public final static String ADD_TO_GROUP_4 = "64";
    public final static String ADD_TO_GROUP_5 = "65";
    public final static String ADD_TO_GROUP_6 = "66";
    public final static String ADD_TO_GROUP_7 = "67";
    public final static String ADD_TO_GROUP_8 = "68";
    public final static String ADD_TO_GROUP_9 = "69";
    public final static String ADD_TO_GROUP_10 = "6A";
    public final static String ADD_TO_GROUP_11 = "6B";
    public final static String ADD_TO_GROUP_12 = "6C";
    public final static String ADD_TO_GROUP_13 = "6D";
    public final static String ADD_TO_GROUP_14 = "6E";
    public final static String ADD_TO_GROUP_15 = "6F";

    public final static String REMOVE_FROM_GROUP_0 = "70";
    public final static String REMOVE_FROM_GROUP_1 = "71";
    public final static String REMOVE_FROM_GROUP_2 = "72";
    public final static String REMOVE_FROM_GROUP_3 = "73";
    public final static String REMOVE_FROM_GROUP_4 = "74";
    public final static String REMOVE_FROM_GROUP_5 = "75";
    public final static String REMOVE_FROM_GROUP_6 = "76";
    public final static String REMOVE_FROM_GROUP_7 = "77";
    public final static String REMOVE_FROM_GROUP_8 = "78";
    public final static String REMOVE_FROM_GROUP_9 = "79";
    public final static String REMOVE_FROM_GROUP_10 = "7A";
    public final static String REMOVE_FROM_GROUP_11 = "7B";
    public final static String REMOVE_FROM_GROUP_12 = "7C";
    public final static String REMOVE_FROM_GROUP_13 = "7D";
    public final static String REMOVE_FROM_GROUP_14 = "7E";

    public final static String SET_SHORT_ADDRESS = "80";
    public final static String ENABLE_WRITE_MEMORY = "81";

    //Queries
    public final static String QUERY_STATUS = "90";
    public final static String QUERY_CONTROL_GEAR_PRESET = "91";
    public final static String QUERY_LAMP_FAILURE = "92";
    public final static String QUERY_LAMP_POWER_ON = "93";
    public final static String QUERY_LIMIT_ERROR = "94";
    public final static String QUERY_RESET_STATE = "95";
    public final static String QUERY_MISSING_SHORT_ADDRESS = "96";
    public final static String QUERY_VERSION_NUMBER = "97";
    public final static String QUERY_CONTENT_DTR0 = "98";
    public final static String QUERY_DEVICE_TYPE = "99";
    public final static String QUERY_PHYSICAL_MINIMUM = "9A";
    public final static String QUERY_POWER_FAILURE = "9B";
    public final static String QUERY_CONTENT_DTR1 = "9C";
    public final static String QUERY_CONTENT_DTR2 = "9D";
    public final static String QUERY_OPERATING_MODE = "9E";
    public final static String QUERY_LIGHT_SOURCE_TYPE = "9F";

    public final static String QUERY_ACTUAL_LEVEL = "A0";
    public final static String QUERY_MAX_LEVEL = "A1";
    public final static String QUERY_MIN_LEVEL = "A2";
    public final static String QUERY_POWER_ON_LEVEL = "A3";
    public final static String QUERY_SYSTEM_FAILURE_LEVEL = "A4";
    public final static String QUERY_FADE_TIME_FADE_RATE = "A5";
    public final static String QUERY_MANUFACTURER_SPECIFIC_MODE = "";
    public final static String QUERY_NEXT_DEVICE_TYPE = "A7";
    public final static String QUERY_EXTENDED_FADE_TIME = "A8";
    public final static String QUERY_CONTROL_GEAR_FAILURE = "AA";

    public final static String QUERY_SCENE_LEVEL_0 = "B0";
    public final static String QUERY_SCENE_LEVEL_1 = "B1";
    public final static String QUERY_SCENE_LEVEL_2 = "B2";
    public final static String QUERY_SCENE_LEVEL_3 = "B3";
    public final static String QUERY_SCENE_LEVEL_4 = "B4";
    public final static String QUERY_SCENE_LEVEL_5 = "B5";
    public final static String QUERY_SCENE_LEVEL_6 = "B6";
    public final static String QUERY_SCENE_LEVEL_7 = "B7";
    public final static String QUERY_SCENE_LEVEL_8 = "B8";
    public final static String QUERY_SCENE_LEVEL_9 = "B9";
    public final static String QUERY_SCENE_LEVEL_10 = "BA";
    public final static String QUERY_SCENE_LEVEL_11 = "BB";
    public final static String QUERY_SCENE_LEVEL_12 = "BC";
    public final static String QUERY_SCENE_LEVEL_13 = "BD";
    public final static String QUERY_SCENE_LEVEL_14 = "BE";
    public final static String QUERY_SCENE_LEVEL_15 = "BF";

    public final static String QUERY_GROUPS_0_TO_7 = "C0";
    public final static String QUERY_GROUPS_8_TO_15 = "C1";
    public final static String QUERY_RANDOM_ADDRESS_H = "C2";
    public final static String QUERY_RANDOM_ADDRESS_M = "C3";
    public final static String QUERY_RANDOM_ADDRESS_L = "C4";
    public final static String READ_MEMORY_LOCATION = "C5";

    //Application Extended Commands
    public final static String QUERY_EXTENDED_VERSION_NUMBER = "FF";

    //Special Commands
    public final static String TERMINATE = "A1";
    public final static String DATA_TRANSFER_REGISTER_0_DTR0 = "A3";
    public final static String INITIALISE = "A5";
    public final static String RANDOMISE = "A7";
    public final static String COMPARE = "A9";
    public final static String WITHDRAW = "AB";
    public final static String PING = "AD";
    public final static String SEARCHADDRH = "B1";
    public final static String SEARCHADDRM = "B3";
    public final static String SEARCHADDRL = "B5";
    public final static String PROGRAM_SHORT_ADDRESS = "B7";
    public final static String VERIFY_SHORT_ADDRESS = "B9";
    public final static String QUERY_SHORT_ADDRESS = "BB";
    public final static String PHYSICAL_SELECTION = "BD";
    public final static String ENABLE_DEVICE_TYPE_X = "C1";
    public final static String DATA_TRANSFER_REGISTER_1_DTR1 = "C3";
    public final static String DATA_TRANSFER_REGISTER_2_DTR2 = "C5";
    public final static String WRITE_MEMORY_LOCATION = "C7";
    public final static String WRITE_MEMORY_LOCATION_NO_REPLY = "C9";

    public final static int BROADCAST_ADRESS= 254;
    public final static int DAPC_OFF= 1; //standard command
    public final static int DAPC_ON= 0; //direct arc power control (DAPC) command
}