var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "120000",
        "ok": "110794",
        "ko": "9206"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "103",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "4073",
        "ok": "3015",
        "ko": "4073"
    },
    "meanResponseTime": {
        "total": "431",
        "ok": "283",
        "ko": "2213"
    },
    "standardDeviation": {
        "total": "628",
        "ok": "317",
        "ko": "709"
    },
    "percentiles1": {
        "total": "158",
        "ok": "147",
        "ko": "2250"
    },
    "percentiles2": {
        "total": "392",
        "ok": "274",
        "ko": "2565"
    },
    "percentiles3": {
        "total": "2117",
        "ok": "997",
        "ko": "3414"
    },
    "percentiles4": {
        "total": "3116",
        "ok": "1594",
        "ko": "3641"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 103299,
        "percentage": 86
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 4043,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 3452,
        "percentage": 3
    },
    "group4": {
        "name": "failed",
        "count": 9206,
        "percentage": 8
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1304.348",
        "ok": "1204.283",
        "ko": "100.065"
    }
},
contents: {
"req_future-da907": {
        type: "REQUEST",
        name: "future",
path: "future",
pathFormatted: "req_future-da907",
stats: {
    "name": "future",
    "numberOfRequests": {
        "total": "120000",
        "ok": "110794",
        "ko": "9206"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "103",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "4073",
        "ok": "3015",
        "ko": "4073"
    },
    "meanResponseTime": {
        "total": "431",
        "ok": "283",
        "ko": "2213"
    },
    "standardDeviation": {
        "total": "628",
        "ok": "317",
        "ko": "709"
    },
    "percentiles1": {
        "total": "159",
        "ok": "147",
        "ko": "2249"
    },
    "percentiles2": {
        "total": "391",
        "ok": "274",
        "ko": "2565"
    },
    "percentiles3": {
        "total": "2117",
        "ok": "997",
        "ko": "3414"
    },
    "percentiles4": {
        "total": "3116",
        "ok": "1595",
        "ko": "3641"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 103299,
        "percentage": 86
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 4043,
        "percentage": 3
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 3452,
        "percentage": 3
    },
    "group4": {
        "name": "failed",
        "count": 9206,
        "percentage": 8
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1304.348",
        "ok": "1204.283",
        "ko": "100.065"
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
