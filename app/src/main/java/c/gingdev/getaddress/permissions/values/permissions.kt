package c.gingdev.getaddress.permissions.values

class permissions {
    /*Storage*/
    val readExternalStorage = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val writeExternalStorage = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    /*SMS*/
    val sendSMS = android.Manifest.permission.SEND_SMS
    val receiveSMS = android.Manifest.permission.RECEIVE_SMS
    val readSMS = android.Manifest.permission.READ_SMS
    val receiveWapPush = android.Manifest.permission.RECEIVE_WAP_PUSH
    val receiveMMS = android.Manifest.permission.RECEIVE_MMS

    /*Sensors*/
    val bodySensors = android.Manifest.permission.BODY_SENSORS

    /*Phone*/
    val readPhoneState = android.Manifest.permission.READ_PHONE_STATE
    val callPhone = android.Manifest.permission.CALL_PHONE
    val readCallLog = android.Manifest.permission.READ_CALL_LOG
    val writeCallLog = android.Manifest.permission.WRITE_CALL_LOG
    val addVoiceMail = android.Manifest.permission.ADD_VOICEMAIL
    val useSip = android.Manifest.permission.USE_SIP
    val processOutGoingCalls = android.Manifest.permission.PROCESS_OUTGOING_CALLS

    /*Microphone*/
    val recordAudio = android.Manifest.permission.RECORD_AUDIO

    /*Location*/
    val accessFineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
    val accessCorseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION

    /*Contacts*/
    val readContacts = android.Manifest.permission.READ_CONTACTS
    val writeContacts = android.Manifest.permission.WRITE_CONTACTS
    val getAccounts = android.Manifest.permission.GET_ACCOUNTS

    /*Camera*/
    val camera = android.Manifest.permission.CAMERA

    /*Calendar*/
    val readCalendar = android.Manifest.permission.READ_CALENDAR
    val writeCalendar = android.Manifest.permission.WRITE_CALENDAR

}