var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "120000",
        "ok": "120000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1305",
        "ok": "1305",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "132",
        "ok": "132",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "39",
        "ok": "39",
        "ko": "-"
    },
    "percentiles1": {
        "total": "119",
        "ok": "119",
        "ko": "-"
    },
    "percentiles2": {
        "total": "137",
        "ok": "137",
        "ko": "-"
    },
    "percentiles3": {
        "total": "200",
        "ok": "200",
        "ko": "-"
    },
    "percentiles4": {
        "total": "263",
        "ok": "263",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 119936,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 53,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 11,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1463.415",
        "ok": "1463.415",
        "ko": "-"
    }
},
contents: {
"req_reactive-bae91": {
        type: "REQUEST",
        name: "reactive",
path: "reactive",
pathFormatted: "req_reactive-bae91",
stats: {
    "name": "reactive",
    "numberOfRequests": {
        "total": "120000",
        "ok": "120000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1305",
        "ok": "1305",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "132",
        "ok": "132",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "39",
        "ok": "39",
        "ko": "-"
    },
    "percentiles1": {
        "total": "119",
        "ok": "119",
        "ko": "-"
    },
    "percentiles2": {
        "total": "137",
        "ok": "137",
        "ko": "-"
    },
    "percentiles3": {
        "total": "200",
        "ok": "200",
        "ko": "-"
    },
    "percentiles4": {
        "total": "263",
        "ok": "263",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 119936,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 53,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 11,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1463.415",
        "ok": "1463.415",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
