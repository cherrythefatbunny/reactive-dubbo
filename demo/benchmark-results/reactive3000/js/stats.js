var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "90000",
        "ok": "90000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1076",
        "ok": "1076",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "120",
        "ok": "120",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "27",
        "ok": "27",
        "ko": "-"
    },
    "percentiles1": {
        "total": "114",
        "ok": "114",
        "ko": "-"
    },
    "percentiles2": {
        "total": "122",
        "ok": "122",
        "ko": "-"
    },
    "percentiles3": {
        "total": "157",
        "ok": "157",
        "ko": "-"
    },
    "percentiles4": {
        "total": "212",
        "ok": "212",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 89974,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 26,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1097.561",
        "ok": "1097.561",
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
        "total": "90000",
        "ok": "90000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "102",
        "ok": "102",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1076",
        "ok": "1076",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "120",
        "ok": "120",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "27",
        "ok": "27",
        "ko": "-"
    },
    "percentiles1": {
        "total": "114",
        "ok": "114",
        "ko": "-"
    },
    "percentiles2": {
        "total": "122",
        "ok": "122",
        "ko": "-"
    },
    "percentiles3": {
        "total": "157",
        "ok": "157",
        "ko": "-"
    },
    "percentiles4": {
        "total": "212",
        "ok": "212",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 89974,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 26,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "1097.561",
        "ok": "1097.561",
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
